package io.codelex.flightplanner.service;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;
import io.codelex.flightplanner.repository.AirportRepository;
import io.codelex.flightplanner.repository.FlightRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "flightplanner", name = "appmode", havingValue = "dbmemory")
public class FlightDatabaseService implements FlightService {
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public FlightDatabaseService(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public void addFlight(Flight flight) {
        if(checkIfSameAirport1(flight)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        checkIfCorrectDates(flight);
        Airport to = checkIfAirportExists(flight.getTo());
        flight.setTo(to);
        Airport from = checkIfAirportExists(flight.getFrom());
        flight.setFrom(from);
        for (Flight i : flightRepository.findAll()) {
            if (checkIfSameFlight(flight, i)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

        }

        flightRepository.save(flight);
    }

    @Override
    public void clearFlights() {
        flightRepository.deleteAll();
    }

    @Override
    public PageResult searchFlights(SearchFlightRequest req) {
        return null;
    }

    @Override
    public Flight fetchById(String id) {
        return null;
    }

    @Override
    public List<Airport> checkIfMatches(String info) {
        return null;
    }

    @Override
    public boolean checkIfSameAirport(Flight f) {
        return false;
    }

    @Override
    public boolean checkIfSameAirport(Airport from, Airport to) {
        return false;
    }


    public boolean checkIfSameAirport3(Airport from, Airport to) {
        List<Airport> airports = airportRepository.findAll();
        for(Airport i : airports){
            if(from.getAirport().equalsIgnoreCase(i.getAirport())|| to.getAirport().equalsIgnoreCase(i.getAirport())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            airportRepository.save(from);
            airportRepository.save(to);
        }
        return false;
    }

    @Override
    public void deleteById(String id) {

    }

    private boolean checkIfSameFlight(Flight f, Flight b) {
        return f.getCarrier().equals(b.getCarrier()) &&
                f.getArrivalTime().equals(b.getArrivalTime()) &&
                f.getDepartureTime().equals(b.getDepartureTime()) &&
                f.getTo().equals(b.getTo()) &&
                f.getFrom().equals(b.getFrom());
    }

    private void checkForDuplicates(Flight flight) {
        for (Flight i : flightRepository.findAll()) {
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

    private Airport checkIfAirportExists(Airport airportReq){
        return airportRepository.findById(airportReq.getAirport())
                .orElse(airportRepository.save(airportReq));

    }


}
