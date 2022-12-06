package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;
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

    boolean checkIfSameAirport(Airport from, Airport to);

    void deleteById(String id);
}
