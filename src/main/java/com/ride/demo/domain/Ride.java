package com.ride.demo.domain;

import com.ride.demo.domain.errors.IllegalRideStatusError;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class Ride {

    private final RideId id;
    private final PassengerId passengerId;
    private final Invoice invoice;
    private final List<Station> stations;
    private final Type type;
    private Status status;

    public Ride(RideId id, PassengerId passengerId, Invoice invoice, List<Station> stations, Type type, Status status) {
        if (status == null)
            throw new IllegalArgumentException("Status could not be null");

        if (type == null)
            throw new IllegalArgumentException("Type could not be null");

        if (passengerId == null)
            throw new IllegalArgumentException("Passenger id could not be null");

        if (invoice == null)
            throw new IllegalArgumentException("Invoice could not be null");

        if (stations == null)
            throw new IllegalArgumentException("Stations could not be null");

        if (stations.isEmpty())
            throw new IllegalArgumentException("Stations size could not be zero");

        this.id = id;
        this.passengerId = passengerId;
        this.invoice = invoice;
        this.stations = stations;
        this.type = type;
        this.status = status;
    }

    public enum Type {
        BIKE, TAXI
    }

    public enum Status {
        PENDING, ACCEPTED, FINISHED, CANCELED;

        public Set<Status> next() {
            if (PENDING.equals(this))
                return Set.of(ACCEPTED, CANCELED);
            else if (ACCEPTED.equals(this))
                return Set.of(FINISHED, CANCELED);
            else
                return Set.of();
        }

    }

    public record RideId(String value) {
    }

    public record PassengerId(String value) {
    }

    public static Ride createRide(PassengerId passengerId, Invoice invoice, List<Station> stations, Type type) {
        return new Ride(
                null,
                passengerId,
                invoice,
                stations,
                type,
                Status.PENDING
        );
    }

    public void accept() {
        if (isInvalidStatus(Status.ACCEPTED))
            throw new IllegalRideStatusError("Can only accept a pending ride");

        this.status = Status.ACCEPTED;
    }

    public void finish() {
        if (isInvalidStatus(Status.FINISHED))
            throw new IllegalRideStatusError("Can only finish an accepted ride");

        this.status = Status.FINISHED;
    }

    public void cancel() {
        if (isInvalidStatus(Status.CANCELED))
            throw new IllegalRideStatusError("Can only cancel a pending or accepted ride");

        this.status = Status.CANCELED;
    }

    private boolean isInvalidStatus(Status newStatus) {
        return !(this.status.next().contains(newStatus));
    }

}
