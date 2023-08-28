package com.ride.demo.application.port.in.commands;

import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public record SubmitRideCommand(
        Ride.Type rideType,
        List<Station> stations,
        Ride.PassengerId passengerId
) {
    public SubmitRideCommand {
        if (rideType == null)
            throw new IllegalArgumentException("rideType must not be null");

        if (passengerId == null || Strings.isEmpty(passengerId.value()))
            throw new IllegalArgumentException("passengerId must not be null or empty");

        if (stations == null || stations.isEmpty())
            throw new IllegalArgumentException("stations must not be null or empty");
    }
}
