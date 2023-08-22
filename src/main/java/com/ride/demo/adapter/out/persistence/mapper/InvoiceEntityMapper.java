package com.ride.demo.adapter.out.persistence.mapper;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import com.ride.demo.domain.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceEntityMapper {
    public InvoiceEntity toEntity(Invoice invoice, Long rideId) {
        if (invoice == null || rideId == null)
            return null;

        var entity = new InvoiceEntity();
        entity.setId(invoice.getId() != null ? Long.parseLong(invoice.getId().value()): null);
        entity.setCommission(invoice.getCommission());
        entity.setPassengerFare(invoice.getPassengerFare());
        entity.setDriverFare(invoice.getDriverFare());
        entity.setRideId(rideId);
        return entity;
    }

    public Invoice toDomain(InvoiceEntity entity) {
        if (entity == null)
            return null;

        return new Invoice(new Invoice.InvoiceId(entity.getId().toString()),
                entity.getDriverFare(),
                entity.getPassengerFare(),
                entity.getCommission());

    }
}
