package com.macan.parkinglot.service.parking;

import com.macan.parkinglot.domain.parking.ParkingSpot;
import com.macan.parkinglot.domain.vehicle.Vehicle;

public interface ParkingSpotService {
    boolean park(ParkingSpot spot, Vehicle vehicle);

    void departing(ParkingSpot spot, Vehicle vehicle);
}
