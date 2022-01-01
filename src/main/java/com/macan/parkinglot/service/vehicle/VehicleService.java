package com.macan.parkinglot.service.vehicle;

import com.macan.parkinglot.domain.parking.ParkingSpot;
import com.macan.parkinglot.domain.vehicle.Vehicle;

public interface VehicleService {
    void parkInSpot(Vehicle v, ParkingSpot spot);

    void departing(Vehicle v);
}
