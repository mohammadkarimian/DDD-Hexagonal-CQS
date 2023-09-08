package com.ride.demo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class RideDTO {
    private Long id;
    private String type;
    private String status;
    private List<StationDTO> stations;
    private InvoiceDTO invoice;
}
