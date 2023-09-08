package com.ride.demo.application.port.in;

import com.ride.demo.application.port.in.commands.CancelRideCommand;

/**
 * The {@code CancelRideUseCase} interface represents a use case for canceling a ride.
 *
 * @author Mohammad Karimian
 */
public interface CancelRideUseCase {

    /**
     * Cancels a ride based on the provided command.
     *
     * @param command The {@link CancelRideCommand} containing information about the ride to be canceled.
     * @throws IllegalArgumentException if the given command is null.
     */
    void cancel(CancelRideCommand command);
}
