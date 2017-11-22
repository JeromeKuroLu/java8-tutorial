package com.unit.test;

import java.util.List;

@FunctionalInterface
public interface ParkingStrategy {
    ParkingLot selectParkingLot(List<ParkingLot> parkingLotList);
}
