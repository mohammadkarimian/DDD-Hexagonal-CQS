package com.ride.demo.application.service;

import com.ride.demo.application.port.in.commands.FinishRideCommand;
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

class FinishRideServiceTest {

    private FinishRideService finishRideService;
    private SaveRidePort saveRidePort;
    private LoadRidePort loadRidePort;

    @BeforeEach
    void init() {
        saveRidePort = mock(SaveRidePort.class);
        loadRidePort = mock(LoadRidePort.class);
        finishRideService = new FinishRideService(loadRidePort, saveRidePort);
    }

    @ParameterizedTest
    @MethodSource({"com.ride.demo.domain.params.RideParameter#acceptedRides"})
    void shouldFinishRideWhenRideIsAccepted(Ride ride) {
        Mockito.when(loadRidePort.load(ride.getId())).thenReturn(ride);
        var command = new FinishRideCommand(ride.getId());

        finishRideService.finish(command);

        ArgumentCaptor<Ride> rideCaptor = ArgumentCaptor.forClass(Ride.class);
        verify(loadRidePort, atLeastOnce()).load(command.id());
        verify(saveRidePort, atLeastOnce()).save(rideCaptor.capture());

        Ride rideCaptorValue = rideCaptor.getValue();
        assertEquals(Ride.Status.FINISHED, rideCaptorValue.getStatus());

    }

    @ParameterizedTest
    @MethodSource({"com.ride.demo.domain.params.RideParameter#finishedRides",
            "com.ride.demo.domain.params.RideParameter#canceledRides",
            "com.ride.demo.domain.params.RideParameter#pendingRides"})
    void shouldNotFinishRideWhenRideIsPendingOrCanceledOrFinished(Ride ride) {
        Mockito.when(loadRidePort.load(ride.getId())).thenReturn(ride);
        var command = new FinishRideCommand(ride.getId());

        assertThrows(IllegalRideStatusError.class,
                () -> finishRideService.finish(command)
        );

        verify(loadRidePort, atLeastOnce()).load(command.id());
        verify(saveRidePort, never()).save(any());
    }

    @Test
    void shouldNotFinishRideWithNullCommand() {
        assertThrows(IllegalArgumentException.class,
                () -> finishRideService.finish(null)
        );

        verify(loadRidePort, never()).load(any());
        verify(saveRidePort, never()).save(any());
    }

}
