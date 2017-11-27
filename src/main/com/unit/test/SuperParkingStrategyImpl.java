package com.unit.test;

import java.util.Comparator;
import java.util.List;

public class SuperParkingStrategyImpl implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream().max(Comparator.comparing(ParkingLot::getLeftSpaceRatio)).orElseThrow(NoAvailableParkingSpaceException::new);
    }
}
