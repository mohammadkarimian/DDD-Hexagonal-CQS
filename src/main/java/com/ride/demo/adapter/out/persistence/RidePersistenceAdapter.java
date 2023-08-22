package com.ride.demo.adapter.out.persistence;

import com.ride.demo.adapter.out.persistence.mapper.InvoiceEntityMapper;
import com.ride.demo.adapter.out.persistence.mapper.RideEntityMapper;
import com.ride.demo.adapter.out.persistence.mapper.StationEntityMapper;
import com.ride.demo.adapter.out.persistence.repository.InvoiceRepository;
import com.ride.demo.adapter.out.persistence.repository.RideRepository;
import com.ride.demo.adapter.out.persistence.repository.StationRepository;
import com.ride.demo.application.port.out.LoadRidePort;
import com.ride.demo.application.port.out.SaveRidePort;
import com.ride.demo.domain.Ride;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RidePersistenceAdapter
        implements SaveRidePort, LoadRidePort {

    private final RideRepository rideRepository;
    private final InvoiceRepository invoiceRepository;
    private final StationRepository stationRepository;

    private final RideEntityMapper rideEntityMapper;
    private final InvoiceEntityMapper invoiceEntityMapper;
    private final StationEntityMapper stationEntityMapper;

    @Autowired
    public RidePersistenceAdapter(RideRepository rideRepository,
                                  InvoiceRepository invoiceRepository,
                                  StationRepository stationRepository,
                                  RideEntityMapper rideEntityMapper,
                                  InvoiceEntityMapper invoiceEntityMapper,
                                  StationEntityMapper stationEntityMapper) {
        this.rideRepository = rideRepository;
        this.invoiceRepository = invoiceRepository;
        this.stationRepository = stationRepository;
        this.rideEntityMapper = rideEntityMapper;
        this.invoiceEntityMapper = invoiceEntityMapper;
        this.stationEntityMapper = stationEntityMapper;
    }

    @Override
    @Transactional
    public void save(Ride ride) {
        var rideEntity = rideRepository.save(rideEntityMapper.toEntity(ride));
        invoiceRepository.save(invoiceEntityMapper.toEntity(ride.getInvoice(), rideEntity.getId()));
        ride.getStations()
                .stream()
                .map(station -> stationEntityMapper.toEntity(station, rideEntity.getId()))
                .forEach(stationRepository::save);
    }

    @Override
    public Ride load(Ride.RideId id) {
        var rideEntity = rideRepository.findById(Long.parseLong(id.value())).orElseThrow(EntityNotFoundException::new);
        var invoice = invoiceEntityMapper.toDomain(invoiceRepository.findFirstByRideId(rideEntity.getId()));
        var stations = stationRepository.findByRideId(rideEntity.getId())
                .stream()
                .map(stationEntityMapper::toDomain)
                .toList();
        return rideEntityMapper.toDomain(rideEntity, stations, invoice);
    }

}
