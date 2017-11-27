package com.unit.test;

import java.util.List;
import java.util.Objects;

public class ParkingBoy implements Anchorable, Printable, Statistic {
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

    @Override
    public boolean hasCar(String carNum) {
        return parkingLotList.stream().anyMatch(lot -> lot.hasCar(carNum));
    }

    @Override
    public boolean isAvailable() {
        return parkingLotList.stream().anyMatch(lot -> lot.isAvailable());
    }

    @Override
    public String print(int layerIndex) {
        StringBuilder indentBuilder = new StringBuilder("\n");
        int nextLayerIndex = layerIndex + 1;
        while(layerIndex > 0) {
            indentBuilder.append("\t");
            layerIndex--;
        }
        return  indentBuilder.toString() + "B " + getAvailableSpacesNum() + " " + getParkedCarNum()
                + parkingLotList.stream().map(lot -> lot.print(nextLayerIndex)).reduce((s1, s2) -> s1 + s2).orElse("");
    }

    @Override
    public int getAvailableSpacesNum() {
        return parkingLotList.stream().mapToInt(ParkingLot::getAvailableSpacesNum).sum();
    }

    @Override
    public int getParkedCarNum() {
        return parkingLotList.stream().mapToInt(ParkingLot::getParkedCarNum).sum();
    }
}
