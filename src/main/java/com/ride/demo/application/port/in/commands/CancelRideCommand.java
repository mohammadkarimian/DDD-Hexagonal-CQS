package com.ride.demo.application.port.in.commands;

import com.ride.demo.domain.Ride;
import org.apache.logging.log4j.util.Strings;

public record CancelRideCommand(
        Ride.RideId id
) {
    public CancelRideCommand {
        if (id == null || Strings.isEmpty(id.value()))
            throw new IllegalArgumentException("id must not be null or empty");
    }
}

