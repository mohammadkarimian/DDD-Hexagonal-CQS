package com.ride.demo.adapter.out.persistence.mapper;

import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import com.ride.demo.domain.Invoice;
import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RideEntityMapper {

    public RideEntity toEntity(Ride ride) {
        if (ride == null)
            return null;

        var entity = new RideEntity();
        entity.setId(ride.getId() != null ? Long.parseLong(ride.getId().value()) : null);
        entity.setType(ride.getType() != null ? ride.getType().name() : null);
        entity.setStatus(ride.getStatus() != null ? ride.getStatus().name() : null);
        entity.setPassengerId(ride.getPassengerId() != null ? ride.getPassengerId().value() : null);
        entity.setVersion(ride.getVersion());
        return entity;
    }

    public Ride toDomain(RideEntity entity, List<Station> stations, Invoice invoice) {
        if (entity == null)
            return null;

        return new Ride(new Ride.RideId(entity.getId().toString()),
                new Ride.PassengerId(entity.getPassengerId()),
                invoice,
                stations,
                Ride.Type.valueOf(entity.getType()),
                Ride.Status.valueOf(entity.getStatus()),
                entity.getVersion());
    }
}
