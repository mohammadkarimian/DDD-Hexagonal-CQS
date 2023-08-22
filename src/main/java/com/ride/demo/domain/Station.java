package com.ride.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Station {

    private final StationId id;
    private final Integer idx;
    private final Type type;
    private final Point point;

    public Station(StationId id, Integer idx, Type type, Point point) {
        if (type == null)
            throw new IllegalArgumentException("Type could not be null");

        if (idx == null || idx < 0)
            throw new IllegalArgumentException("Invalid idx");

        this.id = id;
        this.idx = idx;
        this.type = type;
        this.point = point;
    }

    public enum Type {
        PICKUP,
        DELIVER
    }

    public record Point(Double lat, Double lng) {
        public Point {
            if (lat == null || lng == null)
                throw new IllegalArgumentException("Latitude or Longitude could not be null");

            if (!((lat >= -90.0 && lat <= 90.0) && (lng >= -180.0 && lng <= 180.0)))
                throw new IllegalArgumentException("Invalid point");
        }
    }

    public record StationId(String value) {
    }
}
