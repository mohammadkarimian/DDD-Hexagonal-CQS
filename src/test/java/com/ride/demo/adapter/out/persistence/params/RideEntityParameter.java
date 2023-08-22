package com.ride.demo.adapter.out.persistence.params;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import org.junit.jupiter.params.provider.Arguments;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class RideEntityParameter {
    public static Stream<Arguments> validPendingRides() {
        var rideEntity = new RideEntity(null, "1234", "BIKE", "PENDING", LocalDateTime.now(), LocalDateTime.now());
        var invoiceEntity = new InvoiceEntity(null, BigDecimal.valueOf(90000), BigDecimal.valueOf(100000), BigDecimal.valueOf(10000), LocalDateTime.now(), LocalDateTime.now(), null);
        var stationEntities = List.of(
                new StationEntity(null, 1.0, 2.0, 1, "PICKUP", LocalDateTime.now(), LocalDateTime.now(), null),
                new StationEntity(null, 2.0, 3.0, 2, "DELIVER", LocalDateTime.now(), LocalDateTime.now(), null));

        return Stream.of(Arguments.of(rideEntity, invoiceEntity, stationEntities));
    }

    public static Stream<RideEntity> validRideEntities() {
        var rideEntity = new RideEntity(1L, "1234", "BIKE", "PENDING", LocalDateTime.now(), LocalDateTime.now());

        return Stream.of(rideEntity);
    }
}
