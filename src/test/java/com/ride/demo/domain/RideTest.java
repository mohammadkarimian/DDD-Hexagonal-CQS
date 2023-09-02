package com.ride.demo.domain;

import com.ride.demo.domain.errors.IllegalRideStatusError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RideTest {

    @Test
    void shouldCreateRideWithValidValues() {
        var pickupStation = new Station(new Station.StationId("1"), 1, Station.Type.PICKUP, new Station.Point(0.0, 0.0));
        var dropoffStation = new Station(new Station.StationId("2"), 2, Station.Type.DELIVER, new Station.Point(1.0, 1.0));
        var stations = List.of(pickupStation, dropoffStation);
        var passengerId = new Ride.PassengerId("12345");
        var type = Ride.Type.BIKE;
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));

        var ride = Ride.createRide(passengerId, invoice, stations, type);

        assertNull( ride.getId());
        assertEquals(stations, ride.getStations());
        assertEquals(passengerId, ride.getPassengerId());
        assertEquals(invoice, ride.getInvoice());
        assertEquals(type, ride.getType());
        assertEquals(Ride.Status.PENDING, ride.getStatus());
        assertNull(ride.getVersion());
    }

    @Test
    void shouldThrowExceptionIfPassengerIdIsNull() {
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        var type = Ride.Type.BIKE;
        var status = Ride.Status.PENDING;
        var stations = List.of(new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0)));

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Ride(null, null, invoice, stations, type, status, null));

        assertEquals("Passenger id could not be null", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionIfStatusIsNull() {
        var passengerId = new Ride.PassengerId("12345");
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        var type = Ride.Type.BIKE;
        var stations = List.of(new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0)));

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Ride(null, passengerId, invoice, stations, type, null, null));

        assertEquals("Status could not be null", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionIfTypeIsNull() {
        var passengerId = new Ride.PassengerId("12345");
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        var status = Ride.Status.PENDING;
        var stations = List.of(new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0)));

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Ride(null, passengerId, invoice, stations, null, status, null));

        assertEquals("Type could not be null", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionIfInvoiceIsNull() {
        var passengerId = new Ride.PassengerId("12345");
        var type = Ride.Type.BIKE;
        var status = Ride.Status.PENDING;
        var stations = List.of(new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0)));

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Ride(null, passengerId, null, stations, type, status, null));

        assertEquals("Invoice could not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfStationsIsNull() {
        var passengerId = new Ride.PassengerId("12345");
        var type = Ride.Type.BIKE;
        var status = Ride.Status.PENDING;
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Ride(null, passengerId, invoice, null, type, status, null));

        assertEquals("Stations could not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfStationsIsEmpty() {
        var passengerId = new Ride.PassengerId("12345");
        var type = Ride.Type.BIKE;
        var status = Ride.Status.PENDING;
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        var stations = new ArrayList<Station>();

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Ride(null, passengerId, invoice, stations, type, status, null));

        assertEquals("Stations size could not be zero", exception.getMessage());
    }

    @Test
    void shouldReturnSetOfAcceptedAndCanceledWhenNextCalledFromPending() {
        Ride.Status status = Ride.Status.PENDING;

        Set<Ride.Status> nextStatuses = status.next();

        assertEquals(Set.of(Ride.Status.ACCEPTED, Ride.Status.CANCELED), nextStatuses);
    }

    @Test
    void shouldReturnSetOfFinishedAndCanceledWhenNextCalledFromAccepted() {
        Ride.Status status = Ride.Status.ACCEPTED;

        Set<Ride.Status> nextStatuses = status.next();

        assertEquals(Set.of(Ride.Status.FINISHED, Ride.Status.CANCELED), nextStatuses);
    }

    @Test
    void shouldReturnEmptySetWhenNextCalledFromFinished() {
        Ride.Status status = Ride.Status.FINISHED;

        Set<Ride.Status> nextStatuses = status.next();

        assertEquals(Set.of(), nextStatuses);
    }

    @Test
    void shouldReturnEmptySetWhenNextCalledFromCanceled() {
        Ride.Status status = Ride.Status.CANCELED;

        Set<Ride.Status> nextStatuses = status.next();

        assertEquals(Set.of(), nextStatuses);
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#pendingRides")
    void shouldCancelRideWhenRideIsPending(Ride ride) {
        ride.cancel();

        assertEquals(Ride.Status.CANCELED, ride.getStatus());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#pendingRides")
    void shouldAcceptRideWhenRideIsPending(Ride ride) {
        ride.accept();

        assertEquals(Ride.Status.ACCEPTED, ride.getStatus());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#acceptedRides")
    void shouldFinishRideWhenRideIsAccepted(Ride ride) {
        ride.finish();

        assertEquals(Ride.Status.FINISHED, ride.getStatus());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#acceptedRides")
    void shouldCancelRideWhenRideIsAccepted(Ride ride) {
        ride.cancel();

        assertEquals(Ride.Status.CANCELED, ride.getStatus());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#canceledRides")
    void shouldNotFinishRideWhenRideIsCanceled(Ride ride) {
        var exception =  assertThrows(IllegalRideStatusError.class, ride::finish);

        assertEquals("Can only finish an accepted ride", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#finishedRides")
    void shouldNotCancelRideWhenRideIsFinished(Ride ride) {
        var exception =  assertThrows(IllegalRideStatusError.class, ride::cancel);

        assertEquals("Can only cancel a pending or accepted ride", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#finishedRides")
    void shouldNotAcceptRideWhenRideIsFinished(Ride ride) {
        var exception =  assertThrows(IllegalRideStatusError.class, ride::accept);

        assertEquals("Can only accept a pending ride", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#canceledRides")
    void shouldNotAcceptRideWhenRideIsCanceled(Ride ride) {
        var exception =  assertThrows(IllegalRideStatusError.class, ride::accept);

        assertEquals("Can only accept a pending ride", exception.getMessage());
    }

}
