package io.codelex.flightplanner.repository;


import io.codelex.flightplanner.domain.Flight;
import org.h2.command.query.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {



}
