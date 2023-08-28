package com.ride.demo.application.in.command;

import com.ibm.icu.impl.Assert;
import com.ride.demo.application.port.in.commands.FinishRideCommand;
import com.ride.demo.domain.Ride;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinishRideCommandTest {
    @Test
    void shouldNotInstantiateFinishRideCommandWithNullRideIdValue() {

        try {
            new FinishRideCommand(new Ride.RideId(null));
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("id must not be null or empty", e.getMessage());
        }

    }

    @Test
    void shouldNotInstantiateFinishRideCommandWithEmptyRideIdValue() {

        try {
            new FinishRideCommand(new Ride.RideId(""));
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("id must not be null or empty", e.getMessage());
        }

    }

    @Test
    void shouldNotInstantiateFinishRideCommandWithNullRideId() {

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new FinishRideCommand(null)
        );

        assertEquals("id must not be null or empty", exception.getMessage());

    }
}
