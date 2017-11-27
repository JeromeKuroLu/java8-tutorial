package com.unit.test;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ParkingDirectorTest {
    @Test
    public void should_print_correct_info_given_a_parking_director_who_has_a_manager_with_an_empty_parking_lot_when_print() {
        ParkingManager parkingManager = new ParkingManager(new ParkingLot(1));

        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);
        String result = parkingDirector.print(0);

        assertEquals("\nM 1 0\n\tP 1 0", result);
    }

    @Test
    public void should_print_correct_info_given_a_parking_director_who_has_a_manager_with_an_full_parking_lot_when_print() {
        ParkingManager parkingManager = new ParkingManager(new ParkingLot(1));
        parkingManager.park(new Car("123"));

        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);
        String result = parkingDirector.print(0);

        assertEquals("\nM 0 1\n\tP 0 1", result);
    }

    @Test
    public void should_print_correct_info_given_a_parking_director_who_has_a_manager_with_3_different_parking_boys_when_print() {
        ParkingBoy normalBoy = new ParkingBoy(Arrays.asList(new ParkingLot(3)), new ParkingStrategyImpl());
        ParkingBoy smartBoy = new ParkingBoy(Arrays.asList(new ParkingLot(2)), new SmartParkingStrategyImpl());
        ParkingBoy superBoy= new ParkingBoy(Arrays.asList(new ParkingLot(1)), new SuperParkingStrategyImpl());
        ParkingManager parkingManager = new ParkingManager(normalBoy, smartBoy, superBoy);

        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);
        String result = parkingDirector.print(0);

        assertEquals("\nM 6 0\n\tB 3 0\n\t\tP 3 0\n\tB 2 0\n\t\tP 2 0\n\tB 1 0\n\t\tP 1 0", result);
    }

    @Test
    public void should_print_correct_info_given_a_parking_director_who_has_a_manager_with_3_different_parking_boys_and_some_parked_cars_when_print() {
        ParkingBoy normalBoy = new ParkingBoy(Arrays.asList(new ParkingLot(3)), new ParkingStrategyImpl());
        ParkingBoy smartBoy = new ParkingBoy(Arrays.asList(new ParkingLot(2)), new SmartParkingStrategyImpl());
        ParkingBoy superBoy= new ParkingBoy(Arrays.asList(new ParkingLot(1)), new SuperParkingStrategyImpl());
        ParkingManager parkingManager = new ParkingManager(normalBoy, smartBoy, superBoy);
        parkingManager.park(new Car("123"));

        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);
        String result = parkingDirector.print(0);

        assertEquals("\nM 5 1\n\tB 2 1\n\t\tP 2 1\n\tB 2 0\n\t\tP 2 0\n\tB 1 0\n\t\tP 1 0", result);
    }

    @Test
    public void should_print_correct_info_given_a_parking_director_who_has_a_manager_with_its_own_parking_lots_and_3_different_parking_boys_when_print() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingBoy normalBoy = new ParkingBoy(Arrays.asList(new ParkingLot(3)), new ParkingStrategyImpl());
        ParkingBoy smartBoy = new ParkingBoy(Arrays.asList(new ParkingLot(2)), new SmartParkingStrategyImpl());
        ParkingBoy superBoy= new ParkingBoy(Arrays.asList(new ParkingLot(1)), new SuperParkingStrategyImpl());
        ParkingLot lastLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(firstLot, normalBoy, smartBoy, superBoy, lastLot);

        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);
        String result = parkingDirector.print(0);

        assertEquals("\nM 9 0\n\tP 1 0\n\tB 3 0\n\t\tP 3 0\n\tB 2 0\n\t\tP 2 0\n\tB 1 0\n\t\tP 1 0\n\tP 2 0", result);
    }

    @Test
    public void should_print_correct_info_given_a_parking_director_who_has_a_manager_with_its_own_parking_lots_and_3_different_parking_boys_and_some_parked_cars_when_print() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingBoy normalBoy = new ParkingBoy(Arrays.asList(new ParkingLot(3)), new ParkingStrategyImpl());
        ParkingBoy smartBoy = new ParkingBoy(Arrays.asList(new ParkingLot(2)), new SmartParkingStrategyImpl());
        ParkingBoy superBoy= new ParkingBoy(Arrays.asList(new ParkingLot(1)), new SuperParkingStrategyImpl());
        ParkingLot lastLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(firstLot, normalBoy, smartBoy, superBoy, lastLot);
        parkingManager.park(new Car("123"));
        parkingManager.park(new Car("311"));
        parkingManager.park(new Car("332"));

        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);
        String result = parkingDirector.print(0);

        assertEquals("\nM 6 3\n\tP 0 1\n\tB 1 2\n\t\tP 1 2\n\tB 2 0\n\t\tP 2 0\n\tB 1 0\n\t\tP 1 0\n\tP 2 0", result);
    }
}