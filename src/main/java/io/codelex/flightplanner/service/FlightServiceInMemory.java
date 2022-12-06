package io.codelex.flightplanner.service;

import io.codelex.flightplanner.repository.FlightInMemoryRepository;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "flightplanner", name = "appmode", havingValue = "inmemory")
public class FlightServiceInMemory implements FlightService {
    private final FlightInMemoryRepository repository;

    public FlightServiceInMemory(FlightInMemoryRepository repository) {
        this.repository = repository;

    }
@Override
    public  void addFlight(Flight flight) {
        checkIfCorrectDates(flight);
        checkIfSameAirport(flight);
        checkForDuplicates(flight);
        repository.addFlight(flight);
    }
    @Override
    public  void clearFlights() {
        this.repository.getFlightList().removeAll(repository.getFlightList());
    }

    @Override
    public  PageResult searchFlights(SearchFlightRequest req) {
        if (req.getFrom().equals(req.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ArrayList<Flight> list = new ArrayList<>();
        PageResult result = new PageResult(list);
        for (Flight i : repository.getFlightList()) {
            if (i.getFrom().getAirport().equals(req.getFrom()) &&
                    i.getTo().getAirport().equals(req.getTo()) &&
                    i.getDepartureTime().equals(req.getDepartureDate())) {

                result.getItems().add(i);
            }
        }
        return result;
    }
    @Override
    public  Flight fetchById(String id) {
        for (Flight i : repository.getFlightList()) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @Override
    public  List<Airport> checkIfMatches(String info) {
        List<Airport> airports = new ArrayList<>();
        for (Flight i : repository.getFlightList()) {
            if (i.getFrom().getCountry().toLowerCase().contains(info.trim().toLowerCase()) ||
                    i.getFrom().getCity().toLowerCase().contains(info.trim().toLowerCase()) ||
                    i.getFrom().getAirport().toLowerCase().contains(info.trim().toLowerCase())) {
                airports.add(i.getFrom());
            }
        }
        return airports;
    }
    @Override
    public boolean checkIfSameAirport(Flight f) {
        if (checkIfSameAirport1(f)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return false;
    }

    @Override
    public boolean checkIfSameAirport(Airport from, Airport to) {
        return false;
    }

    @Override
    public  void deleteById(String id) {
        repository.getFlightList().removeIf(flight -> flight.getId().equals(id));

    }

    private boolean checkIfSameFlight(Flight f, Flight b) {
        return f.getCarrier().equals(b.getCarrier()) &&
                f.getArrivalTime().equals(b.getArrivalTime()) &&
                f.getDepartureTime().equals(b.getDepartureTime()) &&
                f.getTo().equals(b.getTo()) &&
                f.getFrom().equals(b.getFrom());
    }
    private void checkForDuplicates(Flight flight) {
        for (Flight i : repository.getFlightList()) {
            if (checkIfSameFlight(flight, i)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }
    }
    private boolean checkIfSameAirport1(Flight f) {
        return f.getFrom().getCountry().trim().equalsIgnoreCase(f.getTo().getCountry().trim()) &&
                f.getFrom().getCity().trim().equalsIgnoreCase(f.getTo().getCity().trim()) &&
                f.getFrom().getAirport().trim().equalsIgnoreCase(f.getTo().getAirport().trim());
    }

    private void checkIfCorrectDates(Flight flight) {
        if (flight.checkIfCorrectDate(flight.getArrivalTime(), flight.getDepartureTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
