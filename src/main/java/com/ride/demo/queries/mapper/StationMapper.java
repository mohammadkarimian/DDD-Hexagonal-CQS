package com.ride.demo.queries.mapper;

import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import com.ride.demo.dtos.StationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationDTO toDTO(StationEntity entity);
    List<StationDTO> toDTOs(List<StationEntity> entity);
}
