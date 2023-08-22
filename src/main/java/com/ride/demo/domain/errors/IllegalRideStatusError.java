package com.ride.demo.domain.errors;

public class IllegalRideStatusError extends BaseError {

    private static final String KEY = "domain.ride.IllegalRideStatusError";

    public IllegalRideStatusError(String message) {
        super(message, KEY);
    }
}
