package com.ride.demo.adapter.out.persistence.mapper;

import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import com.ride.demo.domain.Station;
import org.springframework.stereotype.Component;

@Component
public class StationEntityMapper {
    public StationEntity toEntity(Station station, Long rideId) {
        if (station == null || rideId == null)
            return null;

        var entity = new StationEntity();
        entity.setId(station.getId() != null ? Long.parseLong(station.getId().value()) : null);
        if (station.getPoint() != null) {
            entity.setLat(station.getPoint().lat());
            entity.setLng(station.getPoint().lng());
        }
        entity.setIdx(station.getIdx());
        entity.setType(station.getType() != null ? station.getType().name() : null);
        entity.setRideId(rideId);
        return entity;
    }

    public Station toDomain(StationEntity entity) {
        if (entity == null)
            return null;

        return new Station(new Station.StationId(entity.getId().toString()),
                entity.getIdx(),
                Station.Type.valueOf(entity.getType()),
                new Station.Point(entity.getLat(), entity.getLng()));
    }
}
