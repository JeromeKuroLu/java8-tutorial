package com.unit.test;

public class NoAvailableParkingSpaceException extends RuntimeException {

    public NoAvailableParkingSpaceException() {
        super();
    }

    public NoAvailableParkingSpaceException(String message) {
        super(message);
    }
}
