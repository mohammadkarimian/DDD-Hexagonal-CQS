package com.ride.demo.application.port.in;

import com.ride.demo.application.port.in.commands.SubmitRideCommand;

/**
 * The {@code SubmitRideUseCase} interface represents a use case for submitting a ride.
 *
 * @author Mohammad Karimian
 */
public interface SubmitRideUseCase {

    /**
     * Submits a new ride based on the provided command.
     *
     * @param command The {@link SubmitRideCommand} containing information about the ride to be submitted.
     * @throws IllegalArgumentException if the given command is null.
     */
    void submit(SubmitRideCommand command);
}
