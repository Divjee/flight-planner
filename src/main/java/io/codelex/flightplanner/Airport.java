package io.codelex.flightplanner;

import javax.validation.constraints.NotBlank;

public class Airport {
    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String airport;

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport1)) return false;

        if (!getCountry().equals(airport1.getCountry())) return false;
        if (!getCity().equals(airport1.getCity())) return false;
        return getAirport().equals(airport1.getAirport());
    }

    @Override
    public int hashCode() {
        int result = getCountry().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getAirport().hashCode();
        return result;
    }
}
