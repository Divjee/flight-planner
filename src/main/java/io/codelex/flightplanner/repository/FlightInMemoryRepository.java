package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Flight;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
//@ConditionalOnProperty(prefix = "flightplanner", name = "appmode", havingValue = "inmemory")
public class FlightInMemoryRepository {
    private List<Flight> flightList = new ArrayList<>();

    public FlightInMemoryRepository() {
    }
    public void addFlight(Flight flight) {
        flightList.add(flight);
    }
    public List<Flight> getFlightList() {
        return flightList;
    }
}
