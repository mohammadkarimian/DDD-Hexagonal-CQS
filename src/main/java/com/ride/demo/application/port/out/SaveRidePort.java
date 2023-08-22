package com.ride.demo.application.port.out;

import com.ride.demo.domain.Ride;

public interface SaveRidePort {
    void save(Ride ride);
}
