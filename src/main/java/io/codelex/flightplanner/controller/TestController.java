package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.service.FlightServiceInMemory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestController {

    FlightService flightService;

    public TestController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/clear")
    public synchronized void clearFlights(){
        flightService.clearFlights();
    }
}
