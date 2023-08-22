package com.ride.demo.queries.mapper;

import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StationMapperTest {

    private final StationMapper mapper = Mappers.getMapper(StationMapper.class);

    @ParameterizedTest
    @MethodSource("com.ride.demo.adapter.out.persistence.params.StationEntityParameter#validStations")
    void shouldMapStationEntityToStationDTOWithValidStation(StationEntity entity) {

        var dto = mapper.toDTO(entity);

        assertEquals(entity.getIdx(), dto.getIdx());
        assertEquals(entity.getLng(), dto.getLng());
        assertEquals(entity.getType(), dto.getType().name());
    }
}
