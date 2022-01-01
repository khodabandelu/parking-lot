package com.macan.parkinglot.service.impl.parking;


import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.parking.ParkingFloor;
import com.macan.parkinglot.domain.parking.ParkingLot;
import com.macan.parkinglot.domain.vehicle.Vehicle;
import com.macan.parkinglot.service.parking.ParkingFloorService;
import com.macan.parkinglot.service.parking.ParkingLotService;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingFloorService parkingFloorService;

    public ParkingLotServiceImpl(ParkingFloorService parkingFloorService) {
        this.parkingFloorService = parkingFloorService;
    }


    /**
     * park vehicle in lot instance
     */
    @Override
    public boolean park(ParkingLot parkingLot, Vehicle vehicle) {
        return parkingFloorService.findParkPlace(parkingLot, vehicle);
    }

    /**
     * departing vehicle in lot instance
     */
    @Override
    public void departing(ParkingLot parkingLot, Vehicle vehicle) {
        parkingFloorService.departing(parkingLot, vehicle);
    }

    /**
     * display free spots
     */
    @Override
    public Long freeSpots(ParkingLot parkingLot) {
        return parkingLot.getParkingFloors().values().stream().map(ParkingFloor::getFreeSpots).reduce(0, Integer::sum).longValue();
    }

    /**
     * display total spots
     */
    @Override
    public Long totalSpots(ParkingLot parkingLot) {
        return parkingLot.getParkingFloors().values().stream().flatMap(parkingFloor -> parkingFloor.getParkingSpots().keySet().stream()).count();
    }

    /**
     * display lot is full or not
     */
    @Override
    public boolean isFullParking(ParkingLot parkingLot) {
        return freeSpots(parkingLot) == 0;
    }

    /**
     * display lot is free or not
     */
    @Override
    public boolean isFreeParking(ParkingLot parkingLot) {
        return totalSpots(parkingLot) == freeSpots(parkingLot);
    }

    /**
     * display lot is full by type of spot  or not
     */
    @Override
    public boolean isFullParkingByType(ParkingLot parkingLot, ParkingSpotType parkingSpotType) {
        return parkingLot.getParkingFloors().values().stream().map(f -> parkingFloorService.isFullSpotsByTypeInFloor(f, parkingSpotType)).reduce(true, Boolean::logicalAnd);
    }


}
