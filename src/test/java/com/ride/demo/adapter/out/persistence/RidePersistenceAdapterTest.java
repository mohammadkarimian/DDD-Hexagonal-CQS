package com.ride.demo.adapter.out.persistence;

import com.ride.demo.adapter.out.persistence.entity.InvoiceEntity;
import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import com.ride.demo.adapter.out.persistence.entity.StationEntity;
import com.ride.demo.adapter.out.persistence.repository.InvoiceRepository;
import com.ride.demo.adapter.out.persistence.repository.RideRepository;
import com.ride.demo.adapter.out.persistence.repository.StationRepository;
import com.ride.demo.domain.Ride;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MariaDBContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RidePersistenceAdapterTest {

    private final MariaDBContainer mariaDbContainer;
    private final InvoiceRepository invoiceRepository;
    private final RideRepository rideRepository;
    private final StationRepository stationRepository;
    private final RidePersistenceAdapter adapterUnderTest;

    @Autowired
    RidePersistenceAdapterTest(MariaDBContainer mariaDbContainer,
                               InvoiceRepository invoiceRepository,
                               RideRepository rideRepository,
                               StationRepository stationRepository,
                               RidePersistenceAdapter adapterUnderTest) {
        this.mariaDbContainer = mariaDbContainer;
        this.invoiceRepository = invoiceRepository;
        this.rideRepository = rideRepository;
        this.stationRepository = stationRepository;
        this.adapterUnderTest = adapterUnderTest;
    }

    @BeforeEach
    void clear() {
        stationRepository.deleteAll();
        invoiceRepository.deleteAll();
        rideRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.domain.params.RideParameter#pendingRides")
    void shouldSaveNewRideWithValidValue(Ride ride) {
        adapterUnderTest.save(ride);

        var rideEntities = Lists.newArrayList(rideRepository.findAll().iterator());
        var invoiceEntities = Lists.newArrayList(invoiceRepository.findAll().iterator());
        var stationEntities = Lists.newArrayList(stationRepository.findAll().iterator());

        assertEquals(1, rideEntities.size());
        assertEquals(1, invoiceEntities.size());
        assertEquals(2, stationEntities.size());
        assertNotNull(rideEntities.get(0).getCreatedAt());
        assertNotNull(rideEntities.get(0).getUpdatedAt());
        assertNotNull(invoiceEntities.get(0).getCreatedAt());
        assertNotNull(invoiceEntities.get(0).getUpdatedAt());
        stationEntities.forEach(s -> {
            assertNotNull(s.getCreatedAt());
            assertNotNull(s.getUpdatedAt());
        });
    }

    @ParameterizedTest
    @MethodSource("com.ride.demo.adapter.out.persistence.params.RideEntityParameter#validPendingRides")
    void shouldLoadRideWithValidRideId(RideEntity rideEntity, InvoiceEntity invoiceEntity, List<StationEntity> stationEntities) {
        final var rideId = rideRepository.save(rideEntity).getId();
        invoiceEntity.setRideId(rideId);
        stationEntities.forEach(s -> s.setRideId(rideId));
        invoiceRepository.save(invoiceEntity);
        stationRepository.saveAll(stationEntities);

        var ride = adapterUnderTest.load(new Ride.RideId(rideId.toString()));

        assertNotNull(ride);
        assertNotNull(ride.getInvoice());
        assertNotNull(ride.getStations());
    }

    @Test
    void shouldNotLoadRideWithInValidRideId() {
        assertThrows(EntityNotFoundException.class,
                () -> adapterUnderTest.load(new Ride.RideId("1")));
    }

}
