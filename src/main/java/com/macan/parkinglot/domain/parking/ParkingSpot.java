package com.macan.parkinglot.domain.parking;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.vehicle.Vehicle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingSpot {
    private int row;
    private int spotNumber;
    private String name;
    private ParkingSpotType parkingSpotType;
    private Vehicle vehicle;
    private ParkingFloor parkingFloor;

    public boolean isFree() {
        if (vehicle != null) {
            return false;
        }
        return true;
    }

}
