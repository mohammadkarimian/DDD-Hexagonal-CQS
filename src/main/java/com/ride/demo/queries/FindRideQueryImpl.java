package com.ride.demo.queries;

import com.ride.demo.adapter.out.persistence.repository.InvoiceReadOnlyRepository;
import com.ride.demo.adapter.out.persistence.repository.RideReadOnlyRepository;
import com.ride.demo.adapter.out.persistence.repository.StationReadOnlyRepository;
import com.ride.demo.dtos.RideDTO;
import com.ride.demo.queries.criteria.RideCriteria;
import com.ride.demo.queries.mapper.InvoiceMapper;
import com.ride.demo.queries.mapper.RideMapper;
import com.ride.demo.queries.mapper.StationMapper;
import com.ride.demo.queries.specification.RideSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class FindRideQueryImpl implements FindRideQuery {

    private final RideReadOnlyRepository rideRepository;
    private final InvoiceReadOnlyRepository invoiceRepository;
    private final StationReadOnlyRepository stationRepository;

    private final RideMapper rideMapper;
    private final StationMapper stationMapper;
    private final InvoiceMapper invoiceMapper;

    public FindRideQueryImpl(RideReadOnlyRepository rideRepository,
                             InvoiceReadOnlyRepository invoiceRepository,
                             StationReadOnlyRepository stationRepository,
                             RideMapper rideMapper,
                             StationMapper stationMapper,
                             InvoiceMapper invoiceMapper) {
        this.rideRepository = rideRepository;
        this.invoiceRepository = invoiceRepository;
        this.stationRepository = stationRepository;
        this.rideMapper = rideMapper;
        this.stationMapper = stationMapper;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public Page<RideDTO> findRides(RideCriteria criteria, Pageable pageable) {
        if (criteria == null)
            return Page.empty();

        var spec = RideSpecificationBuilder.builder()
                .setPassengerId(criteria.passengerId())
                .setStatus(criteria.status())
                .setType(criteria.type())
                .build();

        return rideRepository.findAll(spec, pageable)
                .map(rideMapper::toDTO)
                .map(dto -> {
                    dto.setInvoice(invoiceMapper.toDTO(invoiceRepository.findFirstByRideId(dto.getId()).orElse(null)));
                    dto.setStations(stationMapper.toDTOs(stationRepository.findByRideId(dto.getId())));
                    return dto;
                });

    }

}
