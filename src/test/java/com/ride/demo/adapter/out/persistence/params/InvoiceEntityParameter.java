package com.ride.demo.adapter.out.persistence.params;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class InvoiceEntityParameter {

    public static Stream<InvoiceEntity> validInvoices() {
        var invoiceEntity = new InvoiceEntity(1L, BigDecimal.valueOf(90000), BigDecimal.valueOf(100000), BigDecimal.valueOf(10000), LocalDateTime.now(), LocalDateTime.now(), 123L);
        return Stream.of(invoiceEntity);
    }
}
