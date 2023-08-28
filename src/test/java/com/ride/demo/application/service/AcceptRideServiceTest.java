package com.ride.demo.application.service;

import com.ride.demo.application.port.in.commands.AcceptRideCommand;
import com.ride.demo.application.port.out.LoadRidePort;
import com.ride.demo.application.port.out.SaveRidePort;
import com.ride.demo.domain.Ride;
import com.ride.demo.domain.errors.IllegalRideStatusError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AcceptRideServiceTest {

    private AcceptRideService acceptRideService;
    private SaveRidePort saveRidePort;
    private LoadRidePort loadRidePort;

    @BeforeEach
    void init() {
        saveRidePort = mock(SaveRidePort.class);
        loadRidePort = mock(LoadRidePort.class);
        acceptRideService = new AcceptRideService(loadRidePort, saveRidePort);
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#pendingRides")
    void shouldAcceptRideWhenRideIsPending(Ride pendingRide) {
        Mockito.when(loadRidePort.load(pendingRide.getId())).thenReturn(pendingRide);
        var command = new AcceptRideCommand(pendingRide.getId());

        acceptRideService.accept(command);

        ArgumentCaptor<Ride> rideCaptor = ArgumentCaptor.forClass(Ride.class);
        verify(loadRidePort, atLeastOnce()).load(command.id());
        verify(saveRidePort, atLeastOnce()).save(rideCaptor.capture());

        Ride ride = rideCaptor.getValue();
        assertEquals(Ride.Status.ACCEPTED, ride.getStatus());

    }

    @ParameterizedTest
    @MethodSource({"com.ride.demo.domain.params.RideParameter#acceptedRides",
            "com.ride.demo.domain.params.RideParameter#finishedRides",
            "com.ride.demo.domain.params.RideParameter#canceledRides"})
    void shouldNotAcceptRideWhenRideIsNotPending(Ride ride) {
        Mockito.when(loadRidePort.load(ride.getId())).thenReturn(ride);
        var command = new AcceptRideCommand(ride.getId());

        assertThrows(IllegalRideStatusError.class,
                () -> acceptRideService.accept(command)
        );

        verify(loadRidePort, atLeastOnce()).load(command.id());
        verify(saveRidePort, never()).save(any());
    }

    @Test
    void shouldNotAcceptRideWithNullCommand() {
        assertThrows(IllegalArgumentException.class,
                () -> acceptRideService.accept(null)
        );

        verify(loadRidePort, never()).load(any());
        verify(saveRidePort, never()).save(any());
    }


}
