package com.unit.test;

import java.util.List;

public class ParkingStrategyImpl implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream().filter(lot -> lot.getAvailableSpacesNum() > 0).findFirst().orElseThrow(NoAvailableParkingSpaceException::new);
    }
}
