package com.unit.test;

import java.util.List;
import java.util.Objects;

public class ParkingBoy implements Anchorable {
    private List<ParkingLot> parkingLotList;
    private ParkingStrategy parkingStrategy;

    public ParkingBoy(List<ParkingLot> parkingLotList, ParkingStrategy parkingStrategy) {
        this.parkingLotList = parkingLotList;
        this.parkingStrategy = parkingStrategy;
    }

    @Override
    public String park(Car car) {
        ParkingLot parkingLot = parkingStrategy.selectParkingLot(parkingLotList);
        return parkingLot.park(car);
    }

    @Override
    public Car pickUp(String ticket) {
        return parkingLotList.stream()
                .map(lot -> lot.pickUp(ticket))
                .filter(Objects::nonNull).findFirst()
                .orElseThrow(IllegalParkingTicketException::new);
    }
}
