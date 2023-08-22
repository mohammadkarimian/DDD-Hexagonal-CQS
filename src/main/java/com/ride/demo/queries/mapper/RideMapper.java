package com.ride.demo.queries.mapper;

import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import com.ride.demo.dtos.RideDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RideMapper {

    @Mapping(target = "stations", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    RideDTO toDTO(RideEntity rideEntity);

}
