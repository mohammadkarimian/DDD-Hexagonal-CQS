package com.ride.demo.adapter.out.persistence.mapper;

import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import com.ride.demo.domain.Invoice;
import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RideEntityMapperTest {

    private final RideEntityMapper mapper = new RideEntityMapper();

    @Test
    void shouldMapRideToRideEntity() {
        var ride = new Ride(new Ride.RideId("1"), new Ride.PassengerId("1"),
                new Invoice(new Invoice.InvoiceId("1"), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE),
                List.of(new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, new Station.Point(1.0,1.0))),
                Ride.Type.BIKE, Ride.Status.PENDING);

        var entity = mapper.toEntity(ride);

        Assertions.assertEquals(1L, entity.getId());
        Assertions.assertEquals(Ride.Type.BIKE.name(), entity.getType());
        Assertions.assertEquals(Ride.Status.PENDING.name(), entity.getStatus());
        Assertions.assertEquals("1", entity.getPassengerId());
    }

    @Test
    void shouldMapRideToRideEntityIfIdIsNull() {
        var ride = new Ride(null, new Ride.PassengerId("1"),
                new Invoice(new Invoice.InvoiceId("1"), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE),
                List.of(new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, new Station.Point(1.0,1.0))),
                Ride.Type.BIKE, Ride.Status.PENDING);

        var entity = mapper.toEntity(ride);

        assertNull(entity.getId());
        Assertions.assertEquals(Ride.Type.BIKE.name(), entity.getType());
        Assertions.assertEquals(Ride.Status.PENDING.name(), entity.getStatus());
        Assertions.assertEquals("1", entity.getPassengerId());
    }

    @Test
    void shouldReturnNullIfRideIsNull() {
        var entity = mapper.toEntity(null);

        assertNull(entity);
    }

    @Test
    void shouldMapRideEntityToRide() {
        var station = new Station(new Station.StationId("1"), 0, Station.Type.PICKUP, new Station.Point(1.0, 1.0));
        var invoice = new Invoice(new Invoice.InvoiceId("1"), BigDecimal.valueOf(90000), BigDecimal.valueOf(100000), BigDecimal.valueOf(10000));
        var rideEntity = new RideEntity(1L, "1234", "BIKE", "PENDING", LocalDateTime.now(), LocalDateTime.now());

        var domain = mapper.toDomain(rideEntity, List.of(station), invoice);

        assertEquals("1", domain.getId().value());
        assertEquals("1234", domain.getPassengerId().value());
        assertEquals(Ride.Type.BIKE, domain.getType());
        assertEquals(Ride.Status.PENDING, domain.getStatus());
        assertEquals(1, domain.getStations().size());
        assertEquals("1", domain.getStations().get(0).getId().value());
        assertEquals("1", domain.getInvoice().getId().value());
    }

    @Test
    void shouldReturnNullIfRideEntityIsNull() {
        var entity = mapper.toDomain(null, null, null);

        assertNull(entity);
    }
}
