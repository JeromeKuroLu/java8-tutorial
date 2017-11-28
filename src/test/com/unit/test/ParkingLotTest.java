package com.unit.test;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    @Test
    public void should_park_in_a_car_given_1_parking_lot_with_1_empty_space_when_parking() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("123");

        String ticket = parkingLot.park(car);

        assertSame(car, parkingLot.pickUp(ticket));
    }

    @Test(expected = NoAvailableParkingSpaceException.class)
    public void should_return_failure_given_1_parking_log_with_1_occupied_space_when_parking() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("123");
        parkingLot.park(car);
        System.out.println(car.getClass().getSimpleName());

        Car anotherCar = new Car("124");
        parkingLot.park(anotherCar);
    }
}