package com.ride.demo.application.port.in;

import com.ride.demo.application.port.in.commands.SubmitRideCommand;

public interface SubmitRideUseCase {
    void submit(SubmitRideCommand command);
}
