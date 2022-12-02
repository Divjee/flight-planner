package io.codelex.flightplanner;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;

    }

    public synchronized void addFlight(Flight flight) {
        checkIfCorrectDates(flight);
        checkIfSameAirport(flight);
        checkForDuplicates(flight);
        repository.addFlight(flight);
    }

    public synchronized void clearFlights() {
        this.repository.getFlightList().removeAll(repository.getFlightList());
    }


    public synchronized PageResult searchFlights(SearchFlightRequest req) {
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

    public synchronized Flight fetchById(String id) {
        for (Flight i : repository.getFlightList()) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public synchronized List<Airport> checkIfMatches(String info) {
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
    public synchronized void checkIfSameAirport(Flight f) {
        if (checkIfSameAirport1(f)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    public synchronized void deleteById(String id) {
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
