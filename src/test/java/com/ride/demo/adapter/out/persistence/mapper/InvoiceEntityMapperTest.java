package com.ride.demo.adapter.out.persistence.mapper;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import com.ride.demo.domain.Invoice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class InvoiceEntityMapperTest {

    private final InvoiceEntityMapper mapper = new InvoiceEntityMapper();

    @Test
    void shouldMapInvoiceToInvoiceEntity() {
        var invoice = new Invoice(new Invoice.InvoiceId("1"), BigDecimal.valueOf(90000), BigDecimal.valueOf(100000), BigDecimal.valueOf(10000));

        var entity = mapper.toEntity(invoice, 1L);

        assertEquals(1, entity.getId());
        assertEquals(BigDecimal.valueOf(90000), entity.getDriverFare());
        assertEquals(BigDecimal.valueOf(100000), entity.getPassengerFare());
        assertEquals(BigDecimal.valueOf(10000), entity.getCommission());
        assertEquals(1L, entity.getRideId());
    }

    @Test
    void shouldMapInvoiceToInvoiceEntityIfIdIsNull() {
        var invoice = new Invoice(null, BigDecimal.valueOf(90000), BigDecimal.valueOf(100000), BigDecimal.valueOf(10000));

        var entity = mapper.toEntity(invoice, 1L);

        assertNull(entity.getId());
        assertEquals(BigDecimal.valueOf(90000), entity.getDriverFare());
        assertEquals(BigDecimal.valueOf(100000), entity.getPassengerFare());
        assertEquals(BigDecimal.valueOf(10000), entity.getCommission());
        assertEquals(1L, entity.getRideId());
    }

    @Test
    void shouldReturnNullIfInvoiceIsNull() {
        var entity = mapper.toEntity(null, 1L);

        assertNull(entity);
    }

    @Test
    void shouldReturnNullIfRideIdIsNull() {
        var invoice = new Invoice(null, BigDecimal.valueOf(90000), BigDecimal.valueOf(100000), BigDecimal.valueOf(10000));

        var entity = mapper.toEntity(invoice, null);
        assertNull(entity);
    }

    @Test
    void shouldMapInvoiceEntityToInvoice() {
        var invoiceEntity = new InvoiceEntity(1L, BigDecimal.valueOf(90000), BigDecimal.valueOf(100000), BigDecimal.valueOf(10000), LocalDateTime.now(), LocalDateTime.now(), 1L);

        var domain = mapper.toDomain(invoiceEntity);

        assertEquals("1", domain.getId().value());
        assertEquals(BigDecimal.valueOf(90000), domain.getDriverFare());
        assertEquals(BigDecimal.valueOf(100000), domain.getPassengerFare());
        assertEquals(BigDecimal.valueOf(10000), domain.getCommission());
    }

    @Test
    void shouldReturnNullIfInvoiceEntityIsNull() {
        var entity = mapper.toDomain(null);

        assertNull(entity);
    }
}
