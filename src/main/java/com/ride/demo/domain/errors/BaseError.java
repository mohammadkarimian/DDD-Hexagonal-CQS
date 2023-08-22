package com.ride.demo.domain.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseError extends RuntimeException {
    private final String key;

    protected BaseError(String message, String key) {
        super(message);
        this.key = key;
    }
}
