package com.ride.demo.queries.specification;

import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.*;

class RideSpecificationBuilderTest {

    @Test
    void shouldCreateNewRideSpecificationBuilder() {
        RideSpecificationBuilder builder = RideSpecificationBuilder.builder();
        assertNull(builder.getPassengerId());
        assertNull(builder.getType());
        assertNull(builder.getStatus());
    }

    @Test
    void shouldSetPassengerId() {
        RideSpecificationBuilder builder = RideSpecificationBuilder.builder();
        builder.setPassengerId("1234567890");
        assertEquals("1234567890", builder.getPassengerId());
    }

    @Test
    void shouldSetType() {
        RideSpecificationBuilder builder = RideSpecificationBuilder.builder();
        builder.setType("taxi");
        assertEquals("taxi", builder.getType());
    }

    @Test
    void shouldSetStatus() {
        RideSpecificationBuilder builder = RideSpecificationBuilder.builder();
        builder.setStatus("requested");
        assertEquals("requested", builder.getStatus());
    }

    @Test
    void shouldBuildSpecification() {
        RideSpecificationBuilder builder = RideSpecificationBuilder.builder()
                .setPassengerId("1234567890")
                .setType("taxi")
                .setStatus("requested");
        Specification<RideEntity> specification = builder.build();
        assertNotNull(specification);
    }
}
