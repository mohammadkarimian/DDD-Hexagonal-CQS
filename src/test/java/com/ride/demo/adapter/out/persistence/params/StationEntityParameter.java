package com.ride.demo.adapter.out.persistence.params;

import com.ride.demo.adapter.out.persistence.entity.StationEntity;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class StationEntityParameter {

    public static Stream<StationEntity> validStations() {
        var station1 = new StationEntity(1L, 1.0, 2.0, 1, "PICKUP", LocalDateTime.now(), LocalDateTime.now(), 1L);
        var station2 = new StationEntity(2L, 2.0, 3.0, 2, "DELIVER", LocalDateTime.now(), LocalDateTime.now(), 2L);
        return Stream.of(station1, station2);
    }

}
