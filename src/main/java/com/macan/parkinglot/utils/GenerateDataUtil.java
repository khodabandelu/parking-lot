package com.macan.parkinglot.utils;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.parking.ParkingFloor;
import com.macan.parkinglot.domain.parking.ParkingLot;
import com.macan.parkinglot.domain.parking.ParkingSpot;
import com.macan.parkinglot.domain.vehicle.Bus;
import com.macan.parkinglot.domain.vehicle.Car;
import com.macan.parkinglot.domain.vehicle.Motorcycle;
import com.macan.parkinglot.domain.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GenerateDataUtil {
    public static ParkingLot parkingLot = null;
    public static List<Vehicle> vehicles = null;

    public static void generateData(int vehiclesNum) {
        generateParkingData();
        vehicles = generateVehicles(vehiclesNum);
    }

    public static void generateParkingData() {
        HashMap<String, ParkingFloor> parkingFloors = new HashMap<>();

        HashMap<Integer, ParkingSpot> s1 = getParkingSpots(1, 1, 10, 2, 7, 1, 1);
        s1.putAll(getParkingSpots(2, 11, 20, 5, 0, 5, 11));
        s1.putAll(getParkingSpots(3, 21, 30, 2, 1, 7, 21));
        ParkingFloor f1 = ParkingFloor.builder()
                .name("F1")
                .floor(1)
                .rows(3)
                .parkingSpots(s1)
                .freeSpots(s1.size())
                .build();
        HashMap<Integer, ParkingSpot> s2 = getParkingSpots(1, 1, 10, 10, 0, 0, 31);
        s2.putAll(getParkingSpots(2, 11, 20, 4, 2, 4, 41));
        s2.putAll(getParkingSpots(3, 21, 30, 5, 3, 2, 51));

        ParkingFloor f2 = ParkingFloor.builder()
                .name("F2")
                .floor(2)
                .rows(3)
                .parkingSpots(s2)
                .freeSpots(s2.size())
                .build();

        HashMap<Integer, ParkingSpot> s3 = getParkingSpots(1, 1, 10, 7, 0, 3, 61);
        s3.putAll(getParkingSpots(2, 11, 20, 7, 1, 2, 71));
        s3.putAll(getParkingSpots(3, 21, 30, 5, 1, 4, 81));
        ParkingFloor f3 = ParkingFloor.builder()
                .name("F3")
                .floor(3)
                .rows(3)
                .parkingSpots(s3)
                .freeSpots(s3.size())
                .build();

        parkingFloors.put(f1.getName(), f1);
        parkingFloors.put(f2.getName(), f2);
        parkingFloors.put(f3.getName(), f3);
        parkingLot = ParkingLot.builder()
                .name("Almas Mal")
                .parkingFloors(parkingFloors)
                .build();

    }

    static HashMap<Integer, ParkingSpot> getParkingSpots(int row, int startSpotNumber, int endSpotNumber, int carSpotNumber, int motorbikeSpotNumber, int largeSpotNumber, int startSpotNumberInLot) {
        HashMap<Integer, ParkingSpot> ps = new HashMap<>();
        for (int i = startSpotNumber; i <= endSpotNumber && i > 0; i++) {
            ParkingSpot s = ParkingSpot.builder()
                    .name(String.valueOf(Character.toUpperCase(findCharacter(i)) + i))
                    .spotNumber(startSpotNumberInLot + i)
                    .row(row)
                    .parkingSpotType(ParkingSpotType.CAR)
                    .build();
            if (carSpotNumber > 0) {
                s.setParkingSpotType(ParkingSpotType.CAR);
                carSpotNumber--;
            } else if (motorbikeSpotNumber > 0) {
                s.setParkingSpotType(ParkingSpotType.MOTORBIKE);
                motorbikeSpotNumber--;
            } else if (largeSpotNumber > 0) {
                s.setParkingSpotType(ParkingSpotType.LARGE);
                largeSpotNumber--;
            }
            ps.put(s.getSpotNumber(), s);
        }
        return ps;
    }

    public static int findPosition(char inputLetter) {
        // converting input letter in to uniform case.
        char inputLetterToLowerCase = Character.toLowerCase(inputLetter);
        // COnverting chat in to its ascii value
        int asciiValueOfinputChar = (int) inputLetterToLowerCase;
        // ASCII value of lower case letters starts from 97
        int position = asciiValueOfinputChar - 96;
        return position;

    }

    public static char findCharacter(int position) {
        int asciiValue = 96 + position;
        return (char) asciiValue;
    }

    public static List<Vehicle> generateVehicles(int num) {
        List<Vehicle> vehicles = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            int r = random.nextInt(6);
            Vehicle v = null;
            switch (r) {
                case 0:
                    v = new Bus();
                    break;
                case 1, 2:
                    v = new Motorcycle();
                    break;
                case 3, 4, 5:
                    v = new Car();
                    break;
            }
            v.setLicensePlateNumber("vehicle-" + i);
            vehicles.add(v);
        }
        return vehicles;
    }

}
