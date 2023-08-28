package com.ride.demo.application.in.command;

import com.ibm.icu.impl.Assert;
import com.ride.demo.application.port.in.commands.CancelRideCommand;
import com.ride.demo.domain.Ride;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CancelRideCommandTest {
    @Test
    void shouldNotInstantiateCancelRideCommandWithNullRideIdValue() {

        try {
            new CancelRideCommand(new Ride.RideId(null));
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("id must not be null or empty", e.getMessage());
        }

    }

    @Test
    void shouldNotInstantiateCancelRideCommandWithEmptyRideIdValue() {

        try {
            new CancelRideCommand(new Ride.RideId(""));
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("id must not be null or empty", e.getMessage());
        }

    }

    @Test
    void shouldNotInstantiateCancelRideCommandWithNullRideId() {

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new CancelRideCommand(null)
        );

        assertEquals("id must not be null or empty", exception.getMessage());

    }
}
