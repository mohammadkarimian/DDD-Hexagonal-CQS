package com.ride.demo.queries;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import com.ride.demo.adapter.out.persistence.repository.InvoiceRepository;
import com.ride.demo.adapter.out.persistence.repository.RideRepository;
import com.ride.demo.adapter.out.persistence.repository.StationRepository;
import com.ride.demo.queries.criteria.RideCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MariaDBContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FindRideQueryTest {
    private final MariaDBContainer mariaDbContainer;
    private final InvoiceRepository invoiceRepository;
    private final RideRepository rideRepository;
    private final StationRepository stationRepository;
    private final FindRideQuery findRideQuery;

    @Autowired
    FindRideQueryTest(MariaDBContainer mariaDbContainer,
                      InvoiceRepository invoiceRepository,
                      RideRepository rideRepository,
                      StationRepository stationRepository,
                      FindRideQuery findRideQuery) {
        this.mariaDbContainer = mariaDbContainer;
        this.invoiceRepository = invoiceRepository;
        this.rideRepository = rideRepository;
        this.stationRepository = stationRepository;
        this.findRideQuery = findRideQuery;
    }

    @BeforeEach
    void clear() {
        stationRepository.deleteAll();
        invoiceRepository.deleteAll();
        rideRepository.deleteAll();
    }
    @ParameterizedTest
    @MethodSource("com.ride.demo.adapter.out.persistence.params.RideEntityParameter#validPendingRides")
    void shouldQueryRidesWithPendingStatusCriteria(RideEntity rideEntity, InvoiceEntity invoiceEntity, List<StationEntity> stationEntities) {
        final var rideId = rideRepository.save(rideEntity).getId();
        invoiceEntity.setRideId(rideId);
        stationEntities.forEach(s -> s.setRideId(rideId));
        invoiceRepository.save(invoiceEntity);
        stationRepository.saveAll(stationEntities);
        var criteria = new RideCriteria(null,null, "PENDING");
        var pageable = Pageable.ofSize(10);

        var ridesPage = findRideQuery.findRides(criteria, pageable);

        var rideDTO = ridesPage.stream().findFirst().orElse(null);
        assertNotNull(rideDTO);
        assertNotNull(rideDTO.getStations());
        assertNotNull(rideDTO.getInvoice());
        assertEquals(1, ridesPage.getTotalElements());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.adapter.out.persistence.params.RideEntityParameter#validPendingRides")
    void shouldQueryRidesWithBikeTypeCriteria(RideEntity rideEntity, InvoiceEntity invoiceEntity, List<StationEntity> stationEntities) {
        final var rideId = rideRepository.save(rideEntity).getId();
        invoiceEntity.setRideId(rideId);
        stationEntities.forEach(s -> s.setRideId(rideId));
        invoiceRepository.save(invoiceEntity);
        stationRepository.saveAll(stationEntities);
        var criteria = new RideCriteria(null,"BIKE", null);
        var pageable = Pageable.ofSize(10);

        var ridesPage = findRideQuery.findRides(criteria, pageable);

        var rideDTO = ridesPage.stream().findFirst().orElse(null);
        assertNotNull(rideDTO);
        assertNotNull(rideDTO.getStations());
        assertNotNull(rideDTO.getInvoice());
        assertEquals(1, ridesPage.getTotalElements());
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.adapter.out.persistence.params.RideEntityParameter#validPendingRides")
    void shouldQueryRidesWithAcceptedStatusCriteriaAndReturnZero(RideEntity rideEntity, InvoiceEntity invoiceEntity, List<StationEntity> stationEntities) {
        final var rideId = rideRepository.save(rideEntity).getId();
        invoiceEntity.setRideId(rideId);
        stationEntities.forEach(s -> s.setRideId(rideId));
        invoiceRepository.save(invoiceEntity);
        stationRepository.saveAll(stationEntities);
        var criteria = new RideCriteria(null,null, "ACCEPTED");
        var pageable = Pageable.ofSize(10);

        var ridesPage = findRideQuery.findRides(criteria, pageable);

        var rideDTO = ridesPage.stream().findFirst().orElse(null);
        assertNull(rideDTO);
        assertEquals(0, ridesPage.getTotalElements());
    }

    @Test
    void shouldQueryRidesWithNullCriteriaAndReturnEmptyPage() {
        var pageable = Pageable.ofSize(10);

        var ridesPage = findRideQuery.findRides(null, pageable);

        assertTrue(ridesPage.isEmpty());
    }
}
