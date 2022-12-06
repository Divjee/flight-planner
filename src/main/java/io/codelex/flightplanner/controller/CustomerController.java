package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;
import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.service.FlightServiceInMemory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    FlightService flightService;

    public CustomerController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/airports")
    public List<Airport> getAirport(@RequestParam String search) {
        return flightService.checkIfMatches(search);
    }
    @GetMapping(value = "/flights/{id}")
    public synchronized Flight findFlightById(@PathVariable String id) {
        return flightService.fetchById(id);
    }
    @PostMapping("/flights/search")
    public PageResult searchFlights(@Valid @RequestBody SearchFlightRequest req) {
        return flightService.searchFlights(req);
    }
}
