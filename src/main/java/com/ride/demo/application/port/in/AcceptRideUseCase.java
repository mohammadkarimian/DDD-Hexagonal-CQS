package com.ride.demo.application.port.in;

import com.ride.demo.application.port.in.commands.AcceptRideCommand;

public interface AcceptRideUseCase {
    void accept(AcceptRideCommand command);
}
