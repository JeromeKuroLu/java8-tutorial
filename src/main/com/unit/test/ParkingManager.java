package com.unit.test;

import java.util.Arrays;
import java.util.List;

public class ParkingManager implements Anchorable, Statistic {
    private List<Anchorable> anchorableList;

    public ParkingManager(Anchorable... anchorables) {
        this.anchorableList = Arrays.asList(anchorables);
    }

    public ParkingManager(List<Anchorable> anchorableList) {
        this.anchorableList = anchorableList;
    }

    @Override
    public String park(Car car) {
        return anchorableList.stream().filter(Anchorable::isAvailable).findFirst().map(a -> a.park(car)).orElseThrow(NoAvailableParkingSpaceException::new);
    }

    @Override
    public Car pickUp(String ticket) {
        return anchorableList.stream()
                .filter(a -> a.hasCar(ticket.split("~")[1]))
                .findFirst()
                .map(a -> a.pickUp(ticket))
                .orElseThrow(IllegalParkingTicketException::new);
    }

    @Override
    public boolean hasCar(String carNum) {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public int getAvailableSpacesNum() {
        return anchorableList.stream().mapToInt(a -> ((Statistic) a).getAvailableSpacesNum()).sum();
    }

    @Override
    public int getParkedCarNum() {
        return anchorableList.stream().mapToInt(a -> ((Statistic) a).getParkedCarNum()).sum();
    }

    @Override
    public String getStatisticData() {
        return "M " + getAvailableSpacesNum() + " " + getParkedCarNum() + SplitSignalConstant.MANAGER_INNER_SPLIT
                + anchorableList.stream().map(a -> ((Statistic) a).getStatisticData()).reduce((s1, s2) -> s1 + s2).orElse("");
    }

}
