package com.ride.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StationTest {

    @Test
    void shouldCreateStationWithValidValues() {
        var id = new Station.StationId("1");
        var type = Station.Type.PICKUP;
        var idx = 1;
        var point = new Station.Point(0.0, 0.0);

        var station = new Station(id, idx, type, point);

        assertEquals(id.value(), station.getId().value());
        assertEquals(type, station.getType());
        assertEquals(idx, station.getIdx());
        assertEquals(point.lat(), station.getPoint().lat());
        assertEquals(point.lng(), station.getPoint().lng());
    }

    @Test
    void shouldThrowExceptionIfTypeIsNull() {
        var idx = 0;
        var point = new Station.Point(1.0, 2.0);

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Station(null, idx, null, point));

        assertEquals("Type could not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfIdxIsNull() {
        var type = Station.Type.PICKUP;
        var point = new Station.Point(1.0, 2.0);

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Station(null, null, type, point));

        assertEquals("Invalid idx", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfIdxIsNegative() {
        var type = Station.Type.PICKUP;
        var point = new Station.Point(1.0, 2.0);

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Station(null, -1, type, point));

        assertEquals("Invalid idx", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfLatitudeIsOutsideRange() {
        var lng = 200.123;
        var lat = 50.234;

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Station.Point(lat, lng));

        assertEquals("Invalid point", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfLongitudeIsOutsideRange() {
        var lng = 102.1234567;
        var lat = 95.1234567;

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Station.Point(lat, lng));

        assertEquals("Invalid point", exception.getMessage());
    }


    @Test
    void shouldThrowExceptionIfLatitudeIsNull() {
        var lng = 200.123;

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Station.Point(null, lng));

        assertEquals("Latitude or Longitude could not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfLongitudeIsNull() {
        var lat = 50.234123456;

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Station.Point(lat, null));

        assertEquals("Latitude or Longitude could not be null", exception.getMessage());
    }
}
