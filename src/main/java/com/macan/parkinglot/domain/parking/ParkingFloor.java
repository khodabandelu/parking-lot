package com.macan.parkinglot.domain.parking;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

import java.util.HashMap;

@Data
@Builder
@EqualsAndHashCode
public class ParkingFloor {
    private String name;
    private int floor;
    private int rows;
    private HashMap<Integer, ParkingSpot> parkingSpots ;
    private int freeSpots = 0;
}
