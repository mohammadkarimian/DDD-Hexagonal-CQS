package com.ride.demo.dtos;

import com.ride.demo.domain.Station;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class StationDTO {
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;
    @NotNull
    private Station.Type type;
    @NotNull
    @Min(value = 0)
    private Integer idx;
}
