package com.ride.demo.application.port.out;

import com.ride.demo.domain.Ride;

public interface LoadRidePort {
    Ride load(Ride.RideId id);
}
