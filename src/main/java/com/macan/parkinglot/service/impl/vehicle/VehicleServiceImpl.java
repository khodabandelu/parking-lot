package com.macan.parkinglot.service.impl.vehicle;

import com.macan.parkinglot.domain.parking.ParkingSpot;
import com.macan.parkinglot.domain.vehicle.Vehicle;
import com.macan.parkinglot.service.vehicle.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Override
    public void parkInSpot(Vehicle v, ParkingSpot spot) {
        v.getParkingSpots().add(spot);
    }

    @Override
    public void departing(Vehicle v) {
        v.getParkingSpots().clear();
    }

}
