package com.unit.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingLot implements Anchorable {
    int availableSpacesNum;
    private List<Car> parkedCarList;

    public ParkingLot(int availableSpacesNum) {
        this.availableSpacesNum = availableSpacesNum;
        this.parkedCarList = new ArrayList<>();
    }

    @Override
    public String park(Car car) {
        if (hasCar(car.getNum())) {
            throw new ExistentSameNumCarException();
        } else if (availableSpacesNum > 0) {
            parkedCarList.add(car);
            availableSpacesNum--;
            return String.valueOf(parkedCarList.size() - 1) + "~" + car.getNum();
        }
        throw new NoAvailableParkingSpaceException();
    }

    @Override
    public Car pickUp(String ticket) {
        if (ticket != null) {
            String[] spaceIndexAndCarNum = ticket.split("~");
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

    private boolean hasCar(String carNum) {
        return parkedCarList.stream().anyMatch(c -> c.getNum().equals(carNum));
    }

}
