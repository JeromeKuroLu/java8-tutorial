package com.unit.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;

public class ParkingBoyTest {

    @Test
    public void should_park_in_a_car_given_a_parking_boy_with_an_empty_parking_lot_when_parking() throws Exception {
        List<ParkingLot> parkingLotList = Arrays.asList(new ParkingLot(1));
        ParkingStrategy parkingStrategy = new ParkingStrategyImpl();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList, parkingStrategy);
        Car car = new Car("123");

        String ticket = parkingBoy.park(car);

        assertSame(car, parkingLotList.get(0).pickUp(ticket));
    }

    @Test
    public void should_park_in_a_car_given_a_parking_boy_with_2_empty_parking_lots_when_parking() throws Exception {
        List<ParkingLot> parkingLotList = Arrays.asList(new ParkingLot(1), new ParkingLot(2));
        ParkingStrategy parkingStrategy = new ParkingStrategyImpl();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList, parkingStrategy);
        Car car = new Car("123");

        String ticket = parkingBoy.park(car);

        assertSame(car, parkingLotList.get(0).pickUp(ticket));
    }

    @Test
    public void should_park_in_3_car_given_a_parking_boy_with_3_empty_parking_lots_when_parking() throws Exception {
        List<ParkingLot> parkingLotList = Arrays.asList(new ParkingLot(1), new ParkingLot(1), new ParkingLot(2));
        ParkingStrategy parkingStrategy = new ParkingStrategyImpl();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList, parkingStrategy);

        Car car1 = new Car("121");
        String ticket1 = parkingBoy.park(car1);
        Car car2 = new Car("122");
        String ticket2 = parkingBoy.park(car2);
        Car car3 = new Car("123");
        String ticket3 = parkingBoy.park(car3);

        assertSame(car3, parkingLotList.get(2).pickUp(ticket3));
    }

    @Test
    public void should_pick_up_the_car_given_a_parking_boy_with_3_parking_lots_and_3_parked_car_when_picking_up() throws Exception {
        List<ParkingLot> parkingLotList = Arrays.asList(new ParkingLot(1), new ParkingLot(1), new ParkingLot(2));
        ParkingStrategy parkingStrategy = new ParkingStrategyImpl();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList, parkingStrategy);

        Car car1 = new Car("121");
        String ticket1 = parkingBoy.park(car1);
        Car car2 = new Car("122");
        String ticket2 = parkingBoy.park(car2);
        Car car3 = new Car("123");
        String ticket3 = parkingBoy.park(car3);

        assertSame(car1, parkingBoy.pickUp(ticket1));
        assertSame(car2, parkingBoy.pickUp(ticket2));
        assertSame(car3, parkingBoy.pickUp(ticket3));
    }
}
