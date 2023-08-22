package com.ride.demo.application.port.in.commands;

import com.ride.demo.domain.Ride;

public record CancelRideCommand(
        Ride.RideId id
) {
}

