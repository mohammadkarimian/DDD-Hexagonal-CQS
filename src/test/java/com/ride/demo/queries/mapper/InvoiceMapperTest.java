package com.ride.demo.queries.mapper;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceMapperTest {
    private final InvoiceMapper mapper = Mappers.getMapper(InvoiceMapper.class);

    @ParameterizedTest
    @MethodSource("com.ride.demo.adapter.out.persistence.params.InvoiceEntityParameter#validInvoices")
    void shouldMapInvoiceEntityToInvoiceDTOWithValidInvoice(InvoiceEntity entity) {

        var dto = mapper.toDTO(entity);

        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getCommission(), dto.getCommission());
        Assertions.assertEquals(entity.getDriverFare(), dto.getDriverFare());
        Assertions.assertEquals(entity.getPassengerFare(), dto.getPassengerFare());

    }

}
