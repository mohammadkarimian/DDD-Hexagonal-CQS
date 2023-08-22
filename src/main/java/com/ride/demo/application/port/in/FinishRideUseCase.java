package com.ride.demo.application.port.in;

import com.ride.demo.application.port.in.commands.FinishRideCommand;

public interface FinishRideUseCase {
    void finish(FinishRideCommand command);
}
