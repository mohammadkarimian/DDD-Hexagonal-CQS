package com.ride.demo.application.service;

import com.ride.demo.application.port.in.AcceptRideUseCase;
import com.ride.demo.application.port.in.commands.AcceptRideCommand;
import com.ride.demo.application.port.out.LoadRidePort;
import com.ride.demo.application.port.out.SaveRidePort;

public class AcceptRideService implements AcceptRideUseCase {

    private final LoadRidePort loadRidePort;
    private final SaveRidePort saveRidePort;

    public AcceptRideService(LoadRidePort loadRidePort, SaveRidePort saveRidePort) {
        this.loadRidePort = loadRidePort;
        this.saveRidePort = saveRidePort;
    }

    @Override
    public void accept(AcceptRideCommand command) {
        if (command == null)
            throw new IllegalArgumentException("command must not be null");

        var ride = loadRidePort.load(command.id());
        ride.accept();
        saveRidePort.save(ride);
    }
}
