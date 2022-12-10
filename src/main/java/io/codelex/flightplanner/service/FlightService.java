package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FlightService {

    void addFlight(Flight flight);

    void clearFlights();

    PageResult searchFlights(SearchFlightRequest req);

    Flight fetchById(String id);

     List<Airport> checkIfMatches(String info);

     boolean checkIfSameAirport(Flight f);

    HttpStatus deleteById(String id);
}
