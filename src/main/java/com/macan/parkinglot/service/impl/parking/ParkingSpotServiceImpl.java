package com.macan.parkinglot.service.impl.parking;


import com.macan.parkinglot.domain.parking.ParkingSpot;
import com.macan.parkinglot.domain.vehicle.Vehicle;
import com.macan.parkinglot.service.parking.ParkingSpotService;
import com.macan.parkinglot.service.vehicle.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final VehicleService vehicleService;

    public ParkingSpotServiceImpl(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public boolean park(ParkingSpot spot, Vehicle vehicle) {
        if (spot.getVehicle() == null) {
            spot.setVehicle(vehicle);
            vehicleService.parkInSpot(vehicle, spot);
            return true;
        }
        return false;
    }

    @Override
    public void departing(ParkingSpot spot, Vehicle vehicle) {
        spot.setVehicle(null);
        vehicleService.departing(vehicle);
    }

}
