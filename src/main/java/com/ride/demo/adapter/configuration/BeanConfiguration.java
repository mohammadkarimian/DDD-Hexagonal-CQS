package com.ride.demo.adapter.configuration;

import com.ride.demo.DemoApplication;
import com.ride.demo.application.port.in.AcceptRideUseCase;
import com.ride.demo.application.port.in.CancelRideUseCase;
import com.ride.demo.application.port.in.FinishRideUseCase;
import com.ride.demo.application.port.in.SubmitRideUseCase;
import com.ride.demo.application.port.out.LoadRidePort;
import com.ride.demo.application.port.out.SaveRidePort;
import com.ride.demo.application.service.AcceptRideService;
import com.ride.demo.application.service.CancelRideService;
import com.ride.demo.application.service.FinishRideService;
import com.ride.demo.application.service.SubmitRideService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DemoApplication.class)
public class BeanConfiguration {

    @Bean
    SubmitRideUseCase submitRideService(final SaveRidePort saveRidePort) {
        return new SubmitRideService(saveRidePort);
    }

    @Bean
    AcceptRideUseCase acceptRideService(final SaveRidePort saveRidePort,
                                              final LoadRidePort loadRidePort) {
        return new AcceptRideService(loadRidePort, saveRidePort);
    }

    @Bean
    CancelRideUseCase cancelRideService(final SaveRidePort saveRidePort,
                                        final LoadRidePort loadRidePort) {
        return new CancelRideService(loadRidePort, saveRidePort);
    }

    @Bean
    FinishRideUseCase finishRideUseCase(final SaveRidePort saveRidePort,
                                        final LoadRidePort loadRidePort) {
        return new FinishRideService(loadRidePort, saveRidePort);
    }

}