package com.ride.demo.adapter.in.web.req;

import com.ride.demo.dtos.StationDTO;
import com.ride.demo.domain.Ride;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SubmitRideRequest(
        @NotNull
        @Valid
        List<StationDTO> stations,
        @NotNull
        Ride.Type type
) {
}
