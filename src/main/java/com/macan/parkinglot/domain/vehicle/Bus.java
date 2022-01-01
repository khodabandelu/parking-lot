package com.macan.parkinglot.domain.vehicle;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.common.VehicleSize;

public class Bus extends Vehicle {

    public Bus() {
        super(VehicleSize.Large, 5);
    }

    @Override
    public boolean isFitForSpotType(ParkingSpotType parkingSpotType) {
        return false;
    }
}
