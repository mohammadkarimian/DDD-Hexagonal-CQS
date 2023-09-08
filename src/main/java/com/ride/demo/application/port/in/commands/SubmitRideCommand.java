package com.ride.demo.application.port.in.commands;

import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

/**
 * The {@code SubmitRideCommand} record represents a command for submitting a new ride, containing information
 * about the ride type, stations, and passenger ID.
 * It is used to initiate the process of creating and submitting a new ride.
 *
 * @author Mohammad Karimian
 */
public record SubmitRideCommand(
        Ride.Type rideType,
        List<Station> stations,
        Ride.PassengerId passengerId
) {

    /**
     * Constructs a {@code SubmitRideCommand} with the specified ride type, list of stations, and passenger ID.
     *
     * @param rideType    The {@link Ride.Type type} of the ride to be submitted.
     * @param stations    The  list of {@link Station stations} involved in the ride.
     * @param passengerId The ID of the {@link Ride.PassengerId passenger} associated with the ride.
     * @throws IllegalArgumentException if the provided ride type is null, the passenger ID is null or empty,
     *                                  or the list of stations is null or empty.
     */
    public SubmitRideCommand {
        if (rideType == null)
            throw new IllegalArgumentException("rideType must not be null");

        if (passengerId == null || Strings.isEmpty(passengerId.value()))
            throw new IllegalArgumentException("passengerId must not be null or empty");

        if (stations == null || stations.isEmpty())
            throw new IllegalArgumentException("stations must not be null or empty");
    }
}
