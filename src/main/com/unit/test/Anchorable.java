package com.unit.test;

public interface Anchorable {
    String park(Car car);
    Car pickUp(String ticket);
    boolean hasCar(String carNum);
    boolean isAvailable();
}
