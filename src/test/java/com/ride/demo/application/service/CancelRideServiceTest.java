package com.ride.demo.application.service;

import com.ride.demo.application.port.in.commands.CancelRideCommand;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CancelRideServiceTest {

    private CancelRideService cancelRideService;
    private SaveRidePort saveRidePort;
    private LoadRidePort loadRidePort;

    @BeforeEach
    void init() {
        saveRidePort = mock(SaveRidePort.class);
        loadRidePort = mock(LoadRidePort.class);
        cancelRideService = new CancelRideService(loadRidePort, saveRidePort);
    }

    @ParameterizedTest
    @MethodSource({"com.ride.demo.domain.params.RideParameter#pendingRides",
            "com.ride.demo.domain.params.RideParameter#acceptedRides",})
    void shouldCancelRideWhenRideIsPendingOrAccepted(Ride ride) {
        Mockito.when(loadRidePort.load(ride.getId())).thenReturn(ride);
        var command = new CancelRideCommand(ride.getId());

        cancelRideService.cancel(command);

        ArgumentCaptor<Ride> rideCaptor = ArgumentCaptor.forClass(Ride.class);
        verify(loadRidePort, atLeastOnce()).load(command.id());
        verify(saveRidePort, atLeastOnce()).save(rideCaptor.capture());

        Ride rideCaptorValue = rideCaptor.getValue();
        assertEquals(Ride.Status.CANCELED, rideCaptorValue.getStatus());

    }

    @ParameterizedTest
    @MethodSource({"com.ride.demo.domain.params.RideParameter#finishedRides"})
    void shouldNotCancelRideWhenRideIsFinished(Ride ride) {
        Mockito.when(loadRidePort.load(ride.getId())).thenReturn(ride);
        var command = new CancelRideCommand(ride.getId());

        assertThrows(IllegalRideStatusError.class,
                () -> cancelRideService.cancel(command)
        );

        verify(loadRidePort, atLeastOnce()).load(command.id());
        verify(saveRidePort, never()).save(any());
    }

    @Test
    void shouldNotCancelRideWithNullCommand() {
        assertThrows(IllegalArgumentException.class,
                () -> cancelRideService.cancel(null)
        );

        verify(loadRidePort, never()).load(any());
        verify(saveRidePort, never()).save(any());
    }
}
