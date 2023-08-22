package com.ride.demo.application.port.in;

import com.ride.demo.application.port.in.commands.CancelRideCommand;

public interface CancelRideUseCase {
    void cancel(CancelRideCommand command);
}
