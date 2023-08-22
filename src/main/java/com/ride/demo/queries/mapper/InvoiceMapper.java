package com.ride.demo.queries.mapper;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import com.ride.demo.dtos.InvoiceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceDTO toDTO(InvoiceEntity entity);

}
