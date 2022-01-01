package com.macan.parkinglot.service.parking;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.parking.ParkingLot;
import com.macan.parkinglot.domain.vehicle.Vehicle;

public interface ParkingLotService {
    boolean park(ParkingLot parkingLot, Vehicle vehicle);

    void departing(ParkingLot parkingLot, Vehicle vehicle);

    Long freeSpots(ParkingLot parkingLot);

    Long totalSpots(ParkingLot parkingLot);

    boolean isFullParking(ParkingLot parkingLot);

    boolean isFreeParking(ParkingLot parkingLot);

    boolean isFullParkingByType(ParkingLot parkingLot, ParkingSpotType parkingSpotType);
}
