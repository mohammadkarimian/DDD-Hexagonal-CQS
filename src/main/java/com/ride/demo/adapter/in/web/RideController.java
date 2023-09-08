package com.ride.demo.adapter.in.web;

import com.ride.demo.adapter.in.web.req.SubmitRideRequest;
import com.ride.demo.application.port.in.AcceptRideUseCase;
import com.ride.demo.application.port.in.CancelRideUseCase;
import com.ride.demo.application.port.in.FinishRideUseCase;
import com.ride.demo.application.port.in.SubmitRideUseCase;
import com.ride.demo.application.port.in.commands.AcceptRideCommand;
import com.ride.demo.application.port.in.commands.CancelRideCommand;
import com.ride.demo.application.port.in.commands.FinishRideCommand;
import com.ride.demo.application.port.in.commands.SubmitRideCommand;
import com.ride.demo.domain.Ride;
import com.ride.demo.domain.Station;
import com.ride.demo.dtos.RideDTO;
import com.ride.demo.queries.FindRideQuery;
import com.ride.demo.queries.criteria.RideCriteria;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/rides")
public class RideController {

    private final Logger logger = LoggerFactory.getLogger(RideController.class);
    private final SubmitRideUseCase submitRideUseCase;
    private final AcceptRideUseCase acceptRideUseCase;
    private final CancelRideUseCase cancelRideUseCase;
    private final FinishRideUseCase finishRideUseCase;
    private final FindRideQuery findRideQuery;

    @Autowired
    public RideController(SubmitRideUseCase submitRideUseCase,
                          AcceptRideUseCase acceptRideUseCase,
                          CancelRideUseCase cancelRideUseCase,
                          FinishRideUseCase finishRideUseCase,
                          FindRideQuery findRideQuery) {
        this.submitRideUseCase = submitRideUseCase;
        this.acceptRideUseCase = acceptRideUseCase;
        this.cancelRideUseCase = cancelRideUseCase;
        this.finishRideUseCase = finishRideUseCase;
        this.findRideQuery = findRideQuery;
    }

    @PostMapping
    @Operation(summary = "Submit a new Ride")
    public ResponseEntity<Void> submit(@Valid @RequestBody SubmitRideRequest request) {
        logger.info("Received request to submit a ride with payload: {}", request);

        var stations = request.stations()
                .stream()
                .map(dto -> new Station(null, dto.getIdx(), dto.getType(), new Station.Point(dto.getLat(), dto.getLng())))
                .toList();

        submitRideUseCase.submit(new SubmitRideCommand(
                request.type(),
                stations,
                new Ride.PassengerId("12345")
        ));

        logger.info("Ride submission completed");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/accept")
    @Operation(summary = "Accept the ride by its id")
    public ResponseEntity<Void> acceptRide(@PathVariable("id") String rideId) {
        logger.info("Received request to accept a ride with rideId: {}", rideId);

        acceptRideUseCase.accept(new AcceptRideCommand(
                new Ride.RideId(rideId)
        ));

        logger.info("RideId: {} accepted", rideId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel the ride by its id")
    public ResponseEntity<Void> cancelRide(@PathVariable("id") String rideId) {
        logger.info("Received request to cancel a ride with rideId: {}", rideId);

        cancelRideUseCase.cancel(new CancelRideCommand(
                new Ride.RideId(rideId)
        ));

        logger.info("RideId: {} canceled", rideId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/finish")
    @Operation(summary = "Finish the ride by its id")
    public ResponseEntity<Void> finishRide(@PathVariable("id") String rideId) {
        logger.info("Received request to finish a ride with rideId: {}", rideId);

        finishRideUseCase.finish(new FinishRideCommand(
                new Ride.RideId(rideId)
        ));

        logger.info("RideId: {} finished", rideId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Get page of rides by its type, status and passenger-id")
    public ResponseEntity<Page<RideDTO>> getRides(@RequestParam(value = "type", required = false) String type,
                                                  @RequestParam(value = "status", required = false) String status,
                                                  @RequestParam(value = "passenger-id", required = false) String passengerId,
                                                  Pageable pageable) {
        logger.info("Fetching rides with criteria: type={}, status={}, passengerId={}", type, status, passengerId);

        var criteria = new RideCriteria(passengerId, type, status);
        Page<RideDTO> response = findRideQuery.findRides(criteria, pageable);

        logger.info("Returning fetched rides");
        return ResponseEntity.ok(response);
    }
}
