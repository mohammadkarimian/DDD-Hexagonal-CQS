package com.ride.demo.adapter.out.persistence.repository;

import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends CrudRepository<RideEntity, Long> {

}
