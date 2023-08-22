package com.ride.demo.adapter.out.persistence.repository;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceReadOnlyRepository extends ReadOnlyRepository<InvoiceEntity, Long> {
    Optional<InvoiceEntity> findFirstByRideId(Long rideId);
}
