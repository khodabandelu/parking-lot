package com.macan.parkinglot.domain.parking;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;




@Data
@Builder
public class ParkingLot {
    private String name;
    private HashMap<String, ParkingFloor> parkingFloors;

}
