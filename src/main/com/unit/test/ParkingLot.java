package com.unit.test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    int availableSpacesNum;
    List<Car> parkedCarList;

    public ParkingLot(int availableSpacesNum) {
        this.availableSpacesNum = availableSpacesNum;
        this.parkedCarList = new ArrayList<>();
    }

    public String park(Car car) {
        if (availableSpacesNum > 0) {
            parkedCarList.add(car);
            availableSpacesNum--;
            return String.valueOf(parkedCarList.size() - 1) + "~" + car.getNum();
        }
        throw new NoAvailableParkingSpaceException();
    }

    public Car pickUp(String ticket) {
        if (ticket != null) {
            String[] spaceIndexAndCarNum = ticket.split("~");
            String carNum = spaceIndexAndCarNum[1];
            if (hasCar(carNum)) {
                return parkedCarList.remove(Integer.parseInt(spaceIndexAndCarNum[0]));
            }
        }
        return null;
    }

    private boolean hasCar(String carNum) {
        return parkedCarList.stream().anyMatch(c -> c.getNum().equals(carNum));
    }
}
