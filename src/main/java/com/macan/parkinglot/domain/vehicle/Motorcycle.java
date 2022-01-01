package com.macan.parkinglot.domain.vehicle;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.common.VehicleSize;

public class Motorcycle extends Vehicle {
    public Motorcycle() {
        super(VehicleSize.MiniCompact, 1);
    }

    @Override
    public boolean isFitForSpotType(ParkingSpotType parkingSpotType) {
        if (parkingSpotType.equals(ParkingSpotType.MOTORBIKE)) return true;
        return false;
    }
}
