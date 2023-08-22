package com.ride.demo.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Invoice {

    private final InvoiceId id;
    private final BigDecimal driverFare;
    private final BigDecimal passengerFare;
    private final BigDecimal commission;

    public Invoice(InvoiceId id, BigDecimal driverFare, BigDecimal passengerFare, BigDecimal commission) {

        if (driverFare.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Driver Fare could not be negative");

        if (passengerFare.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Passenger Fare could not be negative");

        if (commission.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Commission Fare could not be negative");

        this.id = id;
        this.driverFare = driverFare;
        this.passengerFare = passengerFare;
        this.commission = commission;
    }

    public record InvoiceId(String value) {
    }
}
