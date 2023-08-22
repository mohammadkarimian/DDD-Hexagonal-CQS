package com.ride.demo.application.service;

import com.ride.demo.application.port.in.SubmitRideUseCase;
import com.ride.demo.application.port.in.commands.SubmitRideCommand;
import com.ride.demo.application.port.out.SaveRidePort;
import com.ride.demo.domain.Invoice;
import com.ride.demo.domain.Ride;

import java.math.BigDecimal;

public class SubmitRideService implements SubmitRideUseCase {

    private final SaveRidePort saveRidePort;

    public SubmitRideService(SaveRidePort saveRidePort) {
        this.saveRidePort = saveRidePort;
    }

    @Override
    public void submit(SubmitRideCommand command) {
        Ride ride = Ride.createRide(
                command.passengerId(),
                new Invoice(null, BigDecimal.valueOf(9000), BigDecimal.valueOf(10000), BigDecimal.valueOf(1000)),
                command.stations(),
                command.rideType()
        );

        saveRidePort.save(ride);
    }
}
