package com.ride.demo.application.service;

import com.ride.demo.application.port.in.FinishRideUseCase;
import com.ride.demo.application.port.in.commands.FinishRideCommand;
import com.ride.demo.application.port.out.LoadRidePort;
import com.ride.demo.application.port.out.SaveRidePort;

public class FinishRideService implements FinishRideUseCase {

    private final LoadRidePort loadRidePort;
    private final SaveRidePort saveRidePort;

    public FinishRideService(LoadRidePort loadRidePort, SaveRidePort saveRidePort) {
        this.loadRidePort = loadRidePort;
        this.saveRidePort = saveRidePort;
    }

    @Override
    public void finish(FinishRideCommand command) {
        if (command == null)
            throw new IllegalArgumentException("command must not be null");

        var ride = loadRidePort.load(command.id());
        ride.finish();
        saveRidePort.save(ride);
    }

}
