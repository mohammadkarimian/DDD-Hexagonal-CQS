package com.ride.demo.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvoiceTest {

    @Test
    void shouldCreateInvoiceIdWithValidValues() {
        var id = String.valueOf(new Random().nextLong());

        var invoiceId = new Invoice.InvoiceId(id);

        assertEquals(id, invoiceId.value());
    }

    @Test
    void shouldCreateInvoiceWithValidValues() {
        var id = String.valueOf(new Random().nextLong());
        var invoiceId = new Invoice.InvoiceId(id);
        var driverFare = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);
        var passengerFare = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);
        var commission = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);

        var invoice = new Invoice(invoiceId, driverFare, passengerFare, commission);

        assertEquals(id, invoiceId.value());
        assertEquals(driverFare, invoice.getDriverFare());
        assertEquals(passengerFare, invoice.getPassengerFare());
        assertEquals(commission, invoice.getCommission());
    }

    @Test
    void shouldThrowExceptionIfDriverFareIsNegative() {

        var driverFare = BigDecimal.valueOf(new Random().nextDouble(100000)).multiply(BigDecimal.valueOf(-1)).setScale(2, RoundingMode.HALF_UP);
        var passengerFare = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);
        var commission = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Invoice(null, driverFare, passengerFare, commission));

        assertEquals("Driver Fare could not be negative", exception.getMessage());
    }


    @Test
    void shouldThrowExceptionIfPassengerFareIsNegative() {

        var passengerFare = BigDecimal.valueOf(new Random().nextDouble(100000)).multiply(BigDecimal.valueOf(-1)).setScale(2, RoundingMode.HALF_UP);
        var driverFare = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);
        var commission = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Invoice(null, driverFare, passengerFare, commission));

        assertEquals("Passenger Fare could not be negative", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCommissionIsNegative() {

        var passengerFare = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);
        var driverFare = BigDecimal.valueOf(new Random().nextDouble(100000)).setScale(2, RoundingMode.HALF_UP);
        var commission = BigDecimal.valueOf(new Random().nextDouble(100000)).multiply(BigDecimal.valueOf(-1)).setScale(2, RoundingMode.HALF_UP);

        var exception = assertThrows(IllegalArgumentException.class,
                () -> new Invoice(null, driverFare, passengerFare, commission));

        assertEquals("Commission Fare could not be negative", exception.getMessage());
    }
}
