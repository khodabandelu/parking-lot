package com.macan.parkinglot;

import com.macan.parkinglot.domain.common.ParkingSpotType;
import com.macan.parkinglot.domain.vehicle.Vehicle;
import com.macan.parkinglot.service.parking.ParkingLotService;
import com.macan.parkinglot.utils.GenerateDataUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ParkingLotApplicationTests {

    @Autowired
    private ParkingLotService parkingLotService;

    @BeforeAll
    public static void setup() {
        /**we can use mock server instead of this way of generate data*/
        GenerateDataUtil.generateData(500);
    }

    @RepeatedTest(value = 100, name = RepeatedTest.LONG_DISPLAY_NAME)
    void park(RepetitionInfo repetitionInfo) {
        Vehicle v = GenerateDataUtil.vehicles.get(repetitionInfo.getCurrentRepetition());
        System.out.println("enter Vehicle number:" + repetitionInfo.getCurrentRepetition() + " Type:" + v.getClass().getSimpleName());
        boolean result = parkingLotService.park(GenerateDataUtil.parkingLot, v);
        assertTrue(result, "park has been  failed");
    }

    @RepeatedTest(value = 50, name = RepeatedTest.LONG_DISPLAY_NAME)
    void firstPark(RepetitionInfo repetitionInfo) {
        Vehicle v = GenerateDataUtil.vehicles.get(repetitionInfo.getCurrentRepetition());
        System.out.println("enter Vehicle number:" + repetitionInfo.getCurrentRepetition() + " Type:" + v.getClass().getSimpleName());
        boolean result = parkingLotService.park(GenerateDataUtil.parkingLot, v);
        assertTrue(result, "park has been  failed");
    }


    @BeforeEach
    void beforeEachTest() {
        System.out.println("Before Test -- Parking is " + (parkingLotService.isFreeParking(GenerateDataUtil.parkingLot) ? "free" : "not free"));
        System.out.println("Before Test -- Parking is " + (parkingLotService.isFullParking(GenerateDataUtil.parkingLot) ? "full" : "not full"));
        System.out.println("Before Test -- Number of total Spots:" + parkingLotService.totalSpots(GenerateDataUtil.parkingLot));
        System.out.println("Before Test -- Number of free Spots:" + parkingLotService.freeSpots(GenerateDataUtil.parkingLot));
        System.out.println("Before Test -- " + ParkingSpotType.MOTORBIKE + " Spots is " + (parkingLotService.isFullParkingByType(GenerateDataUtil.parkingLot, ParkingSpotType.MOTORBIKE) ? "full" : "not full"));
        System.out.println("Before Test -- " + ParkingSpotType.CAR + " Spots is " + (parkingLotService.isFullParkingByType(GenerateDataUtil.parkingLot, ParkingSpotType.CAR) ? "full" : "not full"));
        System.out.println("Before Test -- " + ParkingSpotType.LARGE + " Spots is " + (parkingLotService.isFullParkingByType(GenerateDataUtil.parkingLot, ParkingSpotType.LARGE) ? "full" : "not full"));
        System.out.println("=====");
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("After Test -- Parking is " + (parkingLotService.isFreeParking(GenerateDataUtil.parkingLot) ? "free" : "not free"));
        System.out.println("After Test -- Parking is " + (parkingLotService.isFullParking(GenerateDataUtil.parkingLot) ? "full" : "not full"));
        System.out.println("After Test -- Number of total Spots:" + parkingLotService.totalSpots(GenerateDataUtil.parkingLot));
        System.out.println("After Test -- Number of free Spots:" + parkingLotService.freeSpots(GenerateDataUtil.parkingLot));
        System.out.println("After Test -- " + ParkingSpotType.MOTORBIKE + " Spots is " + (parkingLotService.isFullParkingByType(GenerateDataUtil.parkingLot, ParkingSpotType.MOTORBIKE) ? "full" : "not full"));
        System.out.println("After Test -- " + ParkingSpotType.CAR + " Spots is " + (parkingLotService.isFullParkingByType(GenerateDataUtil.parkingLot, ParkingSpotType.CAR) ? "full" : "not full"));
        System.out.println("After Test -- " + ParkingSpotType.LARGE + " Spots is " + (parkingLotService.isFullParkingByType(GenerateDataUtil.parkingLot, ParkingSpotType.LARGE) ? "full" : "not full"));
        System.out.println("=========================================");
    }


}
