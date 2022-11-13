package io.codelex.flightplanner;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    public Flight findFlightById(@PathVariable String id) {
        return flightService.fetchById(id);
    }

    @PostMapping("/flights/search")
    public PageResult searchFlights(@RequestBody SearchFlightRequest req) {
        return flightService.searchFlights(req);
    }
}
