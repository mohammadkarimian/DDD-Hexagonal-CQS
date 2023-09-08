package com.ride.demo.application.service;

import com.ride.demo.application.port.in.commands.SubmitRideCommand;
import com.ride.demo.application.port.out.SaveRidePort;
import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class SubmitRideServiceTest {

    private SaveRidePort saveRidePort;
    private SubmitRideService submitRideService;

    @BeforeEach
    void init() {
        saveRidePort = mock(SaveRidePort.class);
        submitRideService = new SubmitRideService(saveRidePort);
    }

    @Test
    void shouldSubmitRideWhenCommandIsValid() {
        var stations = List.of(new Station(null, 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0)));
        var command = new SubmitRideCommand(Ride.Type.BIKE, stations, new Ride.PassengerId("12345678"));

        submitRideService.submit(command);

        ArgumentCaptor<Ride> rideCaptor = ArgumentCaptor.forClass(Ride.class);
        verify(saveRidePort, times(1)).save(rideCaptor.capture());
        Ride ride = rideCaptor.getValue();
        assertNull(ride.getId());
        assertEquals(Ride.Status.PENDING, ride.getStatus());
        assertEquals(command.rideType(), ride.getType());
        assertEquals(command.stations().size(), ride.getStations().size());
        assertEquals(command.passengerId().value(), ride.getPassengerId().value());
    }

    @Test
    void shouldThrowExceptionWithNullCommand() {
        assertThrows(IllegalArgumentException.class,
                () -> submitRideService.submit(null)
        );

        verify(saveRidePort, never()).save(any());
    }
}
