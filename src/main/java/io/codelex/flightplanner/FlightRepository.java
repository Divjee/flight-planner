package io.codelex.flightplanner;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private List<Flight> flightList = new ArrayList<>();


    public FlightRepository() {
    }
    public void addFlight(Flight flight) {
        flightList.add(flight);
    }
    public List<Flight> getFlightList() {
        return flightList;
    }
}
