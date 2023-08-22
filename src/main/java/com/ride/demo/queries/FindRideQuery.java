package com.ride.demo.queries;

import com.ride.demo.dtos.RideDTO;
import com.ride.demo.queries.criteria.RideCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindRideQuery {
    Page<RideDTO> findRides(RideCriteria criteria, Pageable pageable);
}
