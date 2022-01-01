package com.macan.parkinglot.service.parking;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.parking.ParkingFloor;
import com.macan.parkinglot.domain.parking.ParkingLot;
import com.macan.parkinglot.domain.vehicle.Vehicle;

public interface ParkingFloorService {
    boolean findParkPlace(ParkingLot parkingLot, Vehicle vehicle);


    Integer freeSpotsInFloor(ParkingFloor parkingFloor);

    Long freeSpotsByTypeInFloor(ParkingFloor parkingFloor, ParkingSpotType parkingSpotType);


    Long totalFiledSpotsByTypeInFloor(ParkingFloor parkingFloor, ParkingSpotType parkingSpotType);

    boolean isFullSpotsByTypeInFloor(ParkingFloor parkingFloor, ParkingSpotType parkingSpotType);

    void departing(ParkingLot parkingLot, Vehicle vehicle);
}
