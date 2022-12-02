package io.codelex.flightplanner;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Valid
public class Flight {
    private String id;
    @Valid
    @NotNull
    private Airport from;
    @Valid
    @NotNull
    private Airport to;
    @NotBlank
    private String carrier;
    @NotBlank

    private String departureTime;
    @NotBlank

    private String arrivalTime;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Flight(String id, Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean checkIfCorrectDate(String arrivalTime , String departureTime){
        LocalDateTime localDate = LocalDateTime.parse(arrivalTime,formatter);
        LocalDateTime localDate2 = LocalDateTime.parse(departureTime,formatter);
        return localDate.isBefore(localDate2) || localDate.isEqual(localDate2);
    }

}

