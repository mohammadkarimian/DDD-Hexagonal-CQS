package com.ride.demo.application.service;

import com.ride.demo.application.port.in.AcceptRideUseCase;
import com.ride.demo.application.port.in.commands.AcceptRideCommand;
import com.ride.demo.application.port.out.LoadRidePort;
import com.ride.demo.application.port.out.SaveRidePort;
import org.apache.logging.log4j.util.Strings;

public class AcceptRideService implements AcceptRideUseCase {

    private final LoadRidePort loadRidePort;
    private final SaveRidePort saveRidePort;

    public AcceptRideService(LoadRidePort loadRidePort, SaveRidePort saveRidePort) {
        this.loadRidePort = loadRidePort;
        this.saveRidePort = saveRidePort;
    }

    @Override
    public void accept(AcceptRideCommand command) {
        checkCommand(command);

        var ride = loadRidePort.load(command.id());
        ride.accept();
        saveRidePort.save(ride);
    }

    private void checkCommand(AcceptRideCommand command) {
        if (command == null || command.id() == null || Strings.isEmpty(command.id().value()))
            throw new IllegalArgumentException("invalid command");
    }
}
