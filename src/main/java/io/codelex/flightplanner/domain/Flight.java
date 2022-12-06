package io.codelex.flightplanner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Valid
@Entity(name="flight")
public class Flight {
    @Id
    @NotNull
    private String id;
    @Valid
    @ManyToOne
    //@JoinColumn(name="from_airport", referencedColumnName = "airport")
    private Airport from;
    @Valid
    @ManyToOne
    //@JoinColumn(name="to_airport", referencedColumnName = "airport")
    private Airport to;
    @NotBlank
    private String carrier;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;
    @Transient
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public Flight(String id, Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = LocalDateTime.parse(departureTime,formatter);
        this.arrivalTime = LocalDateTime.parse(arrivalTime,formatter);
    }

    public Flight() {

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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean checkIfCorrectDate(LocalDateTime arrivalTime , LocalDateTime departureTime){
        return arrivalTime.isBefore(departureTime) || arrivalTime.isEqual(departureTime);
    }

}

