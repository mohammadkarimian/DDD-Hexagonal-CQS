package com.ride.demo.application.service;

import com.ride.demo.application.port.in.FinishRideUseCase;
import com.ride.demo.application.port.in.commands.FinishRideCommand;
import com.ride.demo.application.port.out.LoadRidePort;
import com.ride.demo.application.port.out.SaveRidePort;
import org.apache.logging.log4j.util.Strings;

public class FinishRideService implements FinishRideUseCase {

    private final LoadRidePort loadRidePort;
    private final SaveRidePort saveRidePort;

    public FinishRideService(LoadRidePort loadRidePort, SaveRidePort saveRidePort) {
        this.loadRidePort = loadRidePort;
        this.saveRidePort = saveRidePort;
    }

    @Override
    public void finish(FinishRideCommand command) {
        checkCommand(command);

        var ride = loadRidePort.load(command.id());
        ride.finish();
        saveRidePort.save(ride);
    }

    private void checkCommand(FinishRideCommand command) {
        if (command == null || command.id() == null || Strings.isEmpty(command.id().value()))
            throw new IllegalArgumentException("invalid command");
    }
}
