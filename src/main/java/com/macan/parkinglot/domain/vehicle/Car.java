package com.macan.parkinglot.domain.vehicle;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.common.VehicleSize;


public class Car extends Vehicle {
    public Car() {
        super(VehicleSize.Compact, 1);
    }

    @Override
    public boolean isFitForSpotType(ParkingSpotType parkingSpotType) {
        if (parkingSpotType.equals(ParkingSpotType.CAR) || parkingSpotType.equals(ParkingSpotType.LARGE)) return true;
        return false;
    }
}
