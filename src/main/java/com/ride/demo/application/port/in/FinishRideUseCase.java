package com.ride.demo.application.port.in;

import com.ride.demo.application.port.in.commands.FinishRideCommand;

/**
 * The {@code FinishRideUseCase} interface represents a use case for finishing a ride.
 *
 * @author Mohammad Karimian
 */
public interface FinishRideUseCase {

    /**
     * Completes a ride based on the provided command.
     *
     * @param command The {@link FinishRideCommand} containing information about the ride to be finished.
     * @throws IllegalArgumentException if the given command is null.
     */
    void finish(FinishRideCommand command);
}
