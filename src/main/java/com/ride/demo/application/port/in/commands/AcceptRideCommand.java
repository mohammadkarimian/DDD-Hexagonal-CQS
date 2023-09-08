package com.ride.demo.application.port.in.commands;

import com.ride.demo.domain.Ride;
import org.apache.logging.log4j.util.Strings;

/**
 * The {@code AcceptRideCommand} class represents a command for accepting a ride, encapsulating the ride ID.
 *
 * @author Mohammad Karimian
 */
public record AcceptRideCommand(
        Ride.RideId id
) {
    /**
     * Constructs an {@code AcceptRideCommand} with the specified ride ID.
     *
     * @param id The {@link Ride.RideId} representing the ID of the ride to be accepted.
     * @throws IllegalArgumentException if the provided ride ID is null or empty.
     */
    public AcceptRideCommand {
        if (id == null || Strings.isEmpty(id.value()))
            throw new IllegalArgumentException("id must not be null or empty");
    }
}