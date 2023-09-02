package com.ride.demo.domain.params;

import com.ride.demo.domain.Invoice;
import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class RideParameter {

    public static Stream<Ride> pendingRides() {

        var pickupStation = new Station(new Station.StationId("1"), 1, Station.Type.PICKUP, new Station.Point(0.0, 0.0));
        var dropoffStation = new Station(new Station.StationId("2"), 2, Station.Type.DELIVER, new Station.Point(1.0, 1.0));
        var stations = List.of(pickupStation, dropoffStation);
        var id = new Ride.RideId("1");
        var passengerId = new Ride.PassengerId("12345");
        var type = Ride.Type.BIKE;
        var status = Ride.Status.PENDING;
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        var ride = new Ride(id, passengerId, invoice, stations, type, status, 0);

        return Stream.of(ride);
    }

    public static Stream<Ride> acceptedRides() {

        var pickupStation = new Station(new Station.StationId("1"), 1, Station.Type.PICKUP, new Station.Point(0.0, 0.0));
        var dropoffStation = new Station(new Station.StationId("2"), 2, Station.Type.DELIVER, new Station.Point(1.0, 1.0));
        var stations = List.of(pickupStation, dropoffStation);
        var id = new Ride.RideId("1");
        var passengerId = new Ride.PassengerId("12345");
        var type = Ride.Type.BIKE;
        var status = Ride.Status.ACCEPTED;
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        var ride = new Ride(id, passengerId, invoice, stations, type, status, 1);

        return Stream.of(ride);
    }

    public static Stream<Ride> finishedRides() {

        var pickupStation = new Station(new Station.StationId("1"), 1, Station.Type.PICKUP, new Station.Point(0.0, 0.0));
        var dropoffStation = new Station(new Station.StationId("2"), 2, Station.Type.DELIVER, new Station.Point(1.0, 1.0));
        var stations = List.of(pickupStation, dropoffStation);
        var id = new Ride.RideId("1");
        var passengerId = new Ride.PassengerId("12345");
        var type = Ride.Type.BIKE;
        var status = Ride.Status.FINISHED;
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        var ride = new Ride(id, passengerId, invoice, stations, type, status, 2);

        return Stream.of(ride);
    }


    public static Stream<Ride> canceledRides() {

        var pickupStation = new Station(new Station.StationId("1"), 1, Station.Type.PICKUP, new Station.Point(0.0, 0.0));
        var dropoffStation = new Station(new Station.StationId("2"), 2, Station.Type.DELIVER, new Station.Point(1.0, 1.0));
        var stations = List.of(pickupStation, dropoffStation);
        var id = new Ride.RideId("1");
        var passengerId = new Ride.PassengerId("12345");
        var type = Ride.Type.BIKE;
        var status = Ride.Status.CANCELED;
        var invoice = new Invoice(null, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        var ride = new Ride(id, passengerId, invoice, stations, type, status, 2);

        return Stream.of(ride);
    }

}
