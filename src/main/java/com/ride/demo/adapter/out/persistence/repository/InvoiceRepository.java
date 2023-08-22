package com.ride.demo.adapter.out.persistence.repository;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Long> {
    InvoiceEntity findFirstByRideId(Long rideId);
}
