package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AirportRepository extends JpaRepository<Airport,String> {
}
