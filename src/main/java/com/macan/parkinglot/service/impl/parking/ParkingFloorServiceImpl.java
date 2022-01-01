package com.macan.parkinglot.service.impl.parking;


import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.parking.ParkingFloor;
import com.macan.parkinglot.domain.parking.ParkingLot;
import com.macan.parkinglot.domain.parking.ParkingSpot;
import com.macan.parkinglot.domain.vehicle.Vehicle;
import com.macan.parkinglot.service.parking.ParkingFloorService;
import com.macan.parkinglot.service.parking.ParkingSpotService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingFloorServiceImpl implements ParkingFloorService {

    private final ParkingSpotService parkingSpotService;

    public ParkingFloorServiceImpl(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @Override
    public boolean findParkPlace(ParkingLot parkingLot, Vehicle vehicle) {
        for (ParkingFloor floor : parkingLot.getParkingFloors().values()) {
            ParkingSpot firstParkingSpot = findFreeSpot(floor, vehicle);
            if (firstParkingSpot != null) {
                return startParkingInSpot(floor, firstParkingSpot, vehicle);
            }
        }
        return false;
    }

    ParkingSpot findFreeSpot(ParkingFloor parkingFloor, Vehicle vehicle) {
        if (parkingFloor.getFreeSpots() <= 0)
            return null;
        /**we can use strategy design pattern if we have concern of grow this types*/
        if (vehicle.isFitForSpotType(ParkingSpotType.MOTORBIKE)) {
            return parkingFloor.getParkingSpots().values().stream()
                    .filter(s -> s.getVehicle() == null).findFirst().orElse(null);
        } else if (vehicle.isFitForSpotType(ParkingSpotType.CAR)) {
            return parkingFloor.getParkingSpots().values().stream()
                    .filter(s -> (s.getParkingSpotType().equals(ParkingSpotType.CAR) || s.getParkingSpotType().equals(ParkingSpotType.LARGE)) && s.getVehicle() == null)
                    .findFirst().orElse(null);
        } else if (vehicle.isFitForSpotType(ParkingSpotType.LARGE)) {
            return parkingFloor.getParkingSpots().values().stream()
                    .filter(s -> (s.getParkingSpotType().equals(ParkingSpotType.LARGE)) && s.getVehicle() == null)
                    .findFirst().orElse(null);
        } else if (vehicle.getSpotSize() > 1) {
            for (  int row = 1; row <= parkingFloor.getRows(); row++) {
                List<ParkingSpot> freeParkingSpots = new ArrayList<>();
                for (ParkingSpot spot : parkingFloor.getParkingSpots().values()) {
                    if (spot.getRow() == row && spot.getVehicle() == null && spot.getParkingSpotType().equals(ParkingSpotType.LARGE)) {
                        freeParkingSpots.add(spot);
                    }
                }
                if (freeParkingSpots.size() >= vehicle.getSpotSize()){
                    Integer firstConsecutive = findFirstConsecutiveElement(freeParkingSpots.stream().map(ParkingSpot::getSpotNumber).sorted().collect(Collectors.toList()), vehicle.getSpotSize());
                    if (firstConsecutive != null)
                        return parkingFloor.getParkingSpots().get(firstConsecutive);
                }
            }
            return null;
        }
        return null;
    }

    boolean startParkingInSpot(ParkingFloor parkingFloor, ParkingSpot firstSpot, Vehicle vehicle) {
        if (firstSpot == null)
            return false;
        if (vehicle.getSpotSize() > 1) {
            boolean result = true;
            for (int i = 0; i < vehicle.getSpotSize(); i++) {
                result = result && parkingSpotService.park(parkingFloor.getParkingSpots().get(firstSpot.getSpotNumber() + i), vehicle);
                if (result) fillSpot(parkingFloor);
            }
            return result;
        } else {
            boolean result = parkingSpotService.park(firstSpot, vehicle);
            if (result) fillSpot(parkingFloor);
            return result;
        }
    }

    void beEmptySpot(ParkingFloor parkingFloor) {
        parkingFloor.setFreeSpots(parkingFloor.getFreeSpots() + 1);
    }

    void fillSpot(ParkingFloor parkingFloor) {
        parkingFloor.setFreeSpots(parkingFloor.getFreeSpots() - 1);
    }

    public Integer findFirstConsecutiveElement(List<Integer> list, int consecutiveSize) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) + 1 != list.get(i)) {
                list.remove(list.get(i - 1));
                if (list.size() < consecutiveSize)
                    return null;
            }
        }
        return list.get(0);
    }

    @Override
    public Integer freeSpotsInFloor(ParkingFloor parkingFloor) {
        return parkingFloor.getFreeSpots();
    }

    @Override
    public Long freeSpotsByTypeInFloor(ParkingFloor parkingFloor, ParkingSpotType parkingSpotType) {
        return parkingFloor.getParkingSpots().values().stream()
                .filter(spot -> spot.getParkingSpotType().equals(parkingSpotType) && spot.isFree()).count();
    }

    @Override
    public Long totalFiledSpotsByTypeInFloor(ParkingFloor parkingFloor, ParkingSpotType parkingSpotType) {
        return parkingFloor.getParkingSpots().values().stream()
                .filter(spot -> spot.getParkingSpotType().equals(parkingSpotType) && !spot.isFree()).count();
    }

    @Override
    public boolean isFullSpotsByTypeInFloor(ParkingFloor parkingFloor, ParkingSpotType parkingSpotType) {
        return freeSpotsByTypeInFloor(parkingFloor, parkingSpotType) == 0;
    }

    @Override
    public void departing(ParkingLot parkingLot, Vehicle vehicle) {
        for (ParkingSpot spot : vehicle.getParkingSpots()) {
            parkingSpotService.departing(spot, vehicle);
            beEmptySpot(spot.getParkingFloor());
        }
    }

}
