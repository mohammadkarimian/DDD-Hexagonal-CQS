package com.ride.demo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class InvoiceDTO {
    private Long id;
    private BigDecimal driverFare;
    private BigDecimal passengerFare;
    private BigDecimal commission;
}
