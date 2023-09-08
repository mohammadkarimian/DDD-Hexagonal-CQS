package com.ride.demo.application.port.in;

import com.ride.demo.application.port.in.commands.AcceptRideCommand;

/**
 * The {@code AcceptRideUseCase} interface represents a use case for accepting a ride based on a provided command.
 *
 * @author Mohammad Karimian
 */
public interface AcceptRideUseCase {

    /**
     * Accepts a ride based on the provided command's ride ID.
     *
     * @param command {@link AcceptRideCommand}
     * @throws IllegalArgumentException in case the given command is null
     */
    void accept(AcceptRideCommand command);
}
