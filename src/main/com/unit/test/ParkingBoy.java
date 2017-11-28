package com.unit.test;

import java.util.List;
import java.util.Objects;

public class ParkingBoy implements Anchorable, Statistic {
    private List<ParkingLot> parkingLotList;
    private ParkingStrategy parkingStrategy;
    private PrintFormatter formatter;

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
        return parkingLotList.stream().anyMatch(Anchorable::isAvailable);
    }

    @Override
    public int getAvailableSpacesNum() {
        return parkingLotList.stream().mapToInt(ParkingLot::getAvailableSpacesNum).sum();
    }

    @Override
    public int getParkedCarNum() {
        return parkingLotList.stream().mapToInt(ParkingLot::getParkedCarNum).sum();
    }

    @Override
    public String getStatisticData() {
        String strategyFeature;
        switch (parkingStrategy.getClass().getSimpleName()) {
            case "ParkingStrategyImpl":
                strategyFeature = "N";
                break;
            case "SmartParkingStrategyImpl":
                strategyFeature = "Sm";
                break;
            case "SuperParkingStrategyImpl":
                strategyFeature = "S";
                break;
            default:
                strategyFeature = "";
        }

        return "B(" + strategyFeature + ") " + getAvailableSpacesNum() + " " + getParkedCarNum() + SplitSignalConstant.PARKING_BOY_SPLIT
                + parkingLotList.stream().map(Statistic::getStatisticData).reduce((s1, s2) -> s1 + s2).orElse("");
    }
}
