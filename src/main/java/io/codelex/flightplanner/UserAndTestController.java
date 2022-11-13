package io.codelex.flightplanner;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class UserAndTestController {

    FlightService flightService;

    public UserAndTestController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/clear")
    public void clearFlights(){
        flightService.clearFlights();
    }
}
