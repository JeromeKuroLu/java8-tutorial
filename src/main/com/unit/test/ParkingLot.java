package com.unit.test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot implements Anchorable, Statistic {
    private int totalSpaceNum;
    private int availableSpacesNum;
    private List<Car> parkedCarList;

    public ParkingLot(int spacesNum) {
        this.totalSpaceNum = spacesNum;
        this.availableSpacesNum = spacesNum;
        this.parkedCarList = new ArrayList<>();
    }

    @Override
    public int getAvailableSpacesNum() {
        return availableSpacesNum;
    }

    @Override
    public int getParkedCarNum() {
        return parkedCarList.size();
    }

    @Override
    public String getStatisticData() {
        return "P " + getAvailableSpacesNum() + " " + getParkedCarNum() + SplitSignalConstant.PARKING_LOT_SPLIT;
    }

    public double getLeftSpaceRatio() {
        return availableSpacesNum / (double) totalSpaceNum;
    }

    @Override
    public String park(Car car) {
        if (hasCar(car.getNum())) {
            throw new ExistentSameNumCarException();
        } else if (isAvailable()) {
            parkedCarList.add(car);
            availableSpacesNum--;
            return String.valueOf(parkedCarList.size() - 1) + SplitSignalConstant.TICKET_SPLIT + car.getNum();
        }
        throw new NoAvailableParkingSpaceException();
    }

    @Override
    public Car pickUp(String ticket) {
        if (ticket != null) {
            String[] spaceIndexAndCarNum = ticket.split(SplitSignalConstant.TICKET_SPLIT);
            String carNum = spaceIndexAndCarNum[1];
            int ticketIndex = Integer.parseInt(spaceIndexAndCarNum[0]);

            if (hasCar(carNum) && ticketIndex < parkedCarList.size()) {
                if (parkedCarList.get(ticketIndex).getNum().equals(carNum)) {
                    return parkedCarList.remove(ticketIndex);
                } else {
                    throw new IllegalParkingTicketException();
                }
            }
        }
        return null;
    }

    @Override
    public boolean hasCar(String carNum) {
        return parkedCarList.stream().anyMatch(c -> c.getNum().equals(carNum));
    }

    @Override
    public boolean isAvailable() {
        return availableSpacesNum > 0;
    }

}
