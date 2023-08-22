package com.ride.demo.adapter.in.web;

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
import com.ride.demo.queries.FindRideQuery;
import com.ride.demo.queries.criteria.RideCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = RideController.class)
class RideControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubmitRideUseCase submitRideUseCase;

    @MockBean
    private AcceptRideUseCase acceptRideUseCase;

    @MockBean
    private CancelRideUseCase cancelRideUseCase;

    @MockBean
    private FinishRideUseCase finishRideUseCase;

    @MockBean
    private FindRideQuery findRideQuery;

    @Test
    void shouldSubmitRideWhenRequestIsValid() throws Exception {
        var requestBody = "{ \"type\": \"BIKE\", \"stations\": [{\"lat\" : 1.0, \"lng\": 2.0, \"type\" : \"PICKUP\", \"idx\" : 0 }]}";

        mockMvc.perform(post("/v1/rides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        then(submitRideUseCase).should()
                .submit(new SubmitRideCommand(Ride.Type.BIKE,
                        Collections.singletonList(new Station(null, 0, Station.Type.PICKUP, new Station.Point(1.0, 2.0))),
                        new Ride.PassengerId("12345")));

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "{\"stations\": [{\"lat\" : 1.0, \"lng\": 2.0, \"type\" : \"PICKUP\", \"idx\" : 0 }]}",
            "{ \"type\": \"BIKE\" }",
            "{ \"type\": \"BIKE\", \"stations\": [{\"lat\" : 1.0, \"lng\": 2.0, \"idx\" : 0 }]}",
            "{ \"type\": \"BIKE\", \"stations\": [{\"lat\" : 1.0, \"lng\": 2.0, \"type\" : \"PICKUP\", \"idx\" : -1 }]}",
            "{ \"type\": \"BIKE\", \"stations\": [{ \"type\" : \"PICKUP\", \"idx\" : -1 }]}"
    })
    void shouldReturnBadRequestForInvalidSubmitRideRequests(String requestBody) throws Exception {
        mockMvc.perform(post("/v1/rides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCancelRideWhenRequestIsValid() throws Exception {
        mockMvc.perform(put("/v1/rides/1/cancel")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(cancelRideUseCase).should()
                .cancel(new CancelRideCommand(new Ride.RideId("1")));
    }

    @Test
    void shouldFinishRideWhenRequestIsValid() throws Exception {
        mockMvc.perform(put("/v1/rides/1/finish")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(finishRideUseCase).should()
                .finish(new FinishRideCommand(new Ride.RideId("1")));
    }

    @Test
    void shouldAcceptRideWhenRequestIsValid() throws Exception {
        mockMvc.perform(put("/v1/rides/1/accept")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(acceptRideUseCase).should()
                .accept(new AcceptRideCommand(new Ride.RideId("1")));
    }

    @Test
    void shouldQueryRidesWhenTypeIsValid() throws Exception {
        mockMvc.perform(get("/v1/rides")
                        .param("type", "BIKE")
                        .param("status", "PENDING")
                        .param("passenger-id", "1234")
                        .param("size", "10")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(findRideQuery).should()
                .findRides(new RideCriteria("1234","BIKE", "PENDING"), Pageable.ofSize(10));
    }

}
