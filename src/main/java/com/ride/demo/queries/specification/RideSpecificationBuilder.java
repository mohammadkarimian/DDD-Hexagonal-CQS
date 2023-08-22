package com.ride.demo.queries.specification;

import com.ride.demo.adapter.out.persistence.entity.RideEntity;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

@Getter
public class RideSpecificationBuilder {

    private String passengerId;
    private String type;
    private String status;

    private RideSpecificationBuilder() {
    }

    public static RideSpecificationBuilder builder() {
        return new RideSpecificationBuilder();
    }

    private static Specification<RideEntity> stringAttributeEqual(String attribute, String value) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (value == null || value.isEmpty()) return null;
            return criteriaBuilder.equal(root.get(attribute), value);
        };
    }

    public Specification<RideEntity> build() {
        return (root, query, cb) ->
                where(stringAttributeEqual("passengerId", this.passengerId))
                        .and(stringAttributeEqual("type", this.type))
                        .and(stringAttributeEqual("status", this.status))
                        .toPredicate(root, query, cb);
    }

    public RideSpecificationBuilder setPassengerId(String passengerId) {
        this.passengerId = passengerId;
        return this;
    }

    public RideSpecificationBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public RideSpecificationBuilder setStatus(String status) {
        this.status = status;
        return this;
    }
}
