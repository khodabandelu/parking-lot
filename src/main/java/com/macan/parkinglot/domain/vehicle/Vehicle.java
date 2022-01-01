package com.macan.parkinglot.domain.vehicle;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.common.VehicleSize;
import com.macan.parkinglot.domain.parking.ParkingSpot;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public abstract class Vehicle {
    private String licensePlateNumber;
    private final VehicleSize vehicleSize;
    private final int spotSize;
    private ArrayList<ParkingSpot> parkingSpots= new ArrayList<>();

    protected Vehicle(VehicleSize vehicleSize, int spotSize) {
        this.vehicleSize = vehicleSize;
        this.spotSize = spotSize;
    }

    abstract public boolean isFitForSpotType(ParkingSpotType parkingSpotType);
}
