package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.service.FlightDatabaseService;
import io.codelex.flightplanner.service.FlightService;
import io.codelex.flightplanner.service.FlightServiceInMemory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/admin-api")
public class AdminController {
    FlightService flightService;

    public AdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PutMapping(path = "/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public  Flight addFlight(@Valid @RequestBody AddFlightRequest request) {
        Flight flight = request.toDomain(UUID.randomUUID());
        flightService.addFlight(flight);
        return flight;
    }

    @DeleteMapping(value = "/flights/{id}")
    public void deleteFlight(@PathVariable String id) {
        flightService.deleteById(id);
    }
    @GetMapping(value = "/flights/{id}")
    public synchronized Flight fetchFlight(@PathVariable String id) {
        return flightService.fetchById(id);
    }

}
