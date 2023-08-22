package com.ride.demo.queries.mapper;

import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RideMapperTest {

    private final RideMapper mapper = Mappers.getMapper(RideMapper.class);

    @ParameterizedTest
    @MethodSource("com.ride.demo.adapter.out.persistence.params.RideEntityParameter#validRideEntities")
    void shouldMapRideEntityToRideDTOWithValidRide(RideEntity entity){

        var dto = mapper.toDTO(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getType(), dto.getType());
        assertEquals(entity.getStatus(), dto.getStatus());
    }
}
