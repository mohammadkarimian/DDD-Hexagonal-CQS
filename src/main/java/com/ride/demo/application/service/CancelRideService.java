package com.ride.demo.application.service;

import com.ride.demo.application.port.in.CancelRideUseCase;
import com.ride.demo.application.port.in.commands.CancelRideCommand;
import com.ride.demo.application.port.out.LoadRidePort;
import com.ride.demo.application.port.out.SaveRidePort;

public class CancelRideService implements CancelRideUseCase {

    private final LoadRidePort loadRidePort;
    private final SaveRidePort saveRidePort;

    public CancelRideService(LoadRidePort loadRidePort, SaveRidePort saveRidePort) {
        this.loadRidePort = loadRidePort;
        this.saveRidePort = saveRidePort;
    }

    @Override
    public void cancel(CancelRideCommand command) {
        if (command == null)
            throw new IllegalArgumentException("command must not be null");

        var ride = loadRidePort.load(command.id());
        ride.cancel();
        saveRidePort.save(ride);
    }
}
