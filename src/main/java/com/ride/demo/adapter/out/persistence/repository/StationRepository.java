package com.ride.demo.adapter.out.persistence.repository;

import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends CrudRepository<StationEntity, Long> {
    List<StationEntity> findByRideId(Long rideId);
}
