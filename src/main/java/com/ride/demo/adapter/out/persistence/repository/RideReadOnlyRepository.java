package com.ride.demo.adapter.out.persistence.repository;

import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface RideReadOnlyRepository extends ReadOnlyRepository<RideEntity, Long> {

}
