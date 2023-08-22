package com.ride.demo.application.port.in.commands;

import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;

import java.util.List;

public record SubmitRideCommand(
        Ride.Type rideType,
        List<Station> stations,
        Ride.PassengerId passengerId
) {
}
