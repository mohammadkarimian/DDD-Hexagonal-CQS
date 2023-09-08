package com.ride.demo.application.port.in.commands;

import com.ride.demo.domain.Ride;
import org.apache.logging.log4j.util.Strings;

/**
 * The {@code CancelRideCommand} record represents a command for canceling a ride, containing the ride ID.
 * It is used to initiate the process of canceling a specific ride.
 *
 * @author Mohammad Karimian
 */
public record CancelRideCommand(
        Ride.RideId id
) {

    /**
     * Constructs a {@code CancelRideCommand} with the specified ride ID.
     *
     * @param id The {@link Ride.RideId} representing the ID of the ride to be canceled.
     * @throws IllegalArgumentException if the provided ride ID is null or empty.
     */
    public CancelRideCommand {
        if (id == null || Strings.isEmpty(id.value()))
            throw new IllegalArgumentException("id must not be null or empty");
    }
}

