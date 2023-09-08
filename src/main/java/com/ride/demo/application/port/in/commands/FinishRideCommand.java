package com.ride.demo.application.port.in.commands;

import com.ride.demo.domain.Ride;
import org.apache.logging.log4j.util.Strings;

/**
 * The {@code FinishRideCommand} record represents a command for finishing a ride, containing the ride ID.
 * It is used to initiate the process of completing a specific ride.
 *
 * @author Mohammad Karimian
 */
public record FinishRideCommand(
        Ride.RideId id
) {

    /**
     * Constructs a {@code FinishRideCommand} with the specified ride ID.
     *
     * @param id The {@link Ride.RideId} representing the ID of the ride to be finished.
     * @throws IllegalArgumentException if the provided ride ID is null or empty.
     */
    public FinishRideCommand {
        if (id == null || Strings.isEmpty(id.value()))
            throw new IllegalArgumentException("id must not be null or empty");
    }
}
