package com.unit.test;

import java.util.Arrays;
import java.util.List;

public class ParkingDirector implements Printable {
    private List<ParkingManager> parkingManagerList;

    public ParkingDirector(List<ParkingManager> parkingManagerList) {
        this.parkingManagerList = parkingManagerList;
    }

    public ParkingDirector(ParkingManager... parkingManagers) {
        this.parkingManagerList = Arrays.asList(parkingManagers);
    }


    @Override
    public String print(int layerIndex, PrintFormatter formatter) {
        return parkingManagerList.stream().map(m -> formatter.format(layerIndex, m.getStatisticData())).reduce((s1, s2) -> s1 + s2).orElse("");
    }
}
