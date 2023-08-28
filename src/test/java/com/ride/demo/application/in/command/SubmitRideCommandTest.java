package com.ride.demo.application.in.command;

import com.ibm.icu.impl.Assert;
import com.ride.demo.application.port.in.commands.SubmitRideCommand;
import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubmitRideCommandTest {

    @Test
    void shouldNotInstantiateSubmitRideCommandWithNullRideType() {

        var stations = List.of(new Station(null, 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0)));

        try {
            new SubmitRideCommand(null, stations, new Ride.PassengerId("1234"));
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("rideType must not be null", e.getMessage());
        }

    }

    @Test
    void shouldNotInstantiateSubmitRideCommandWithNullStations() {

        try {
            new SubmitRideCommand(Ride.Type.BIKE, null, new Ride.PassengerId("1234"));
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("stations must not be null or empty", e.getMessage());
        }

    }

    @Test
    void shouldNotInstantiateSubmitRideCommandWithEmptyStations() {

        try {
            new SubmitRideCommand(Ride.Type.BIKE, new ArrayList<>(), new Ride.PassengerId("1234"));
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("stations must not be null or empty", e.getMessage());
        }

    }

    @Test
    void shouldNotInstantiateSubmitRideCommandWithNullPassengerId() {

        var stations = List.of(new Station(null, 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0)));

        try {
            new SubmitRideCommand(Ride.Type.BIKE, stations, null);
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("passengerId must not be null or empty", e.getMessage());
        }

    }

    @Test
    void shouldNotInstantiateSubmitRideCommandWithEmptyPassengerId() {

        var stations = List.of(new Station(null, 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0)));

        try {
            new SubmitRideCommand(Ride.Type.BIKE, stations, new Ride.PassengerId(""));
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("passengerId must not be null or empty", e.getMessage());
        }

    }

}
