package com.unit.test;

import java.util.Comparator;
import java.util.List;

public class SmartParkingStrategyImpl implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream().max(Comparator.comparing(ParkingLot::getAvailableSpacesNum)).orElseThrow(NoAvailableParkingSpaceException::new);
    }
}
