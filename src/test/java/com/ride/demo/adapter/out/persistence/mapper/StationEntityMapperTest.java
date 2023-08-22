package com.ride.demo.adapter.out.persistence.mapper;

import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import com.ride.demo.domain.Station;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class StationEntityMapperTest {

    private final StationEntityMapper mapper = new StationEntityMapper();

    @Test
    void shouldMapStationToStationEntity() {
        var station = new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0));

        var entity = mapper.toEntity(station, 1L);

        assertEquals(1L, entity.getId());
        assertEquals(0, entity.getIdx());
        assertEquals(Station.Type.PICKUP.name(), entity.getType());
        assertEquals(1.0, entity.getLat());
        assertEquals(1.0, entity.getLng());
        assertEquals(1, entity.getRideId());
    }

    @Test
    void shouldMapStationToStationEntityIfIdIsNull() {
        var station = new Station(null, 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0));

        var entity = mapper.toEntity(station, 1L);

        assertNull(entity.getId());
        assertEquals(0, entity.getIdx());
        assertEquals(Station.Type.PICKUP.name(), entity.getType());
        assertEquals(1.0, entity.getLat());
        assertEquals(1.0, entity.getLng());
        assertEquals(1, entity.getRideId());
    }

    @Test
    void shouldMapStationToStationEntityIfPointIsNull() {
        var station = new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, null);

        var entity = mapper.toEntity(station, 1L);

        assertEquals(1L, entity.getId());
        assertEquals(0, entity.getIdx());
        assertEquals(Station.Type.PICKUP.name(), entity.getType());
        assertNull(entity.getLat());
        assertNull(entity.getLng());
        assertEquals(1, entity.getRideId());
    }

    @Test
    void shouldReturnNullIfStationIsNull() {
        var entity = mapper.toEntity(null, 1L);

        assertNull(entity);
    }

    @Test
    void shouldReturnNullIfRideIdIsNull() {
        var station = new Station(null, 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0));

        var entity = mapper.toEntity(station, null);
        assertNull(entity);
    }

    @Test
    void shouldMapStationEntityToStation() {
        var entity = new StationEntity(1L, 1.0, 2.0, 1, "PICKUP", LocalDateTime.now(), LocalDateTime.now(), 1L);

        var domain = mapper.toDomain(entity);

        assertEquals("1", domain.getId().value());
        assertEquals(1, entity.getIdx());
        assertEquals(Station.Type.PICKUP, domain.getType());
        assertEquals(1.0, domain.getPoint().lat());
        assertEquals(2.0, domain.getPoint().lng());
    }

    @Test
    void shouldReturnNullIfStationEntityIsNull() {
        var entity = mapper.toDomain(null);

        assertNull(entity);
    }

}
