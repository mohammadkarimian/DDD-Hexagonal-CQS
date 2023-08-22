package com.ride.demo.adapter.out.persistence.repository;

import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationReadOnlyRepository extends ReadOnlyRepository<StationEntity, Long> {
    List<StationEntity> findByRideId(Long rideId);
}
