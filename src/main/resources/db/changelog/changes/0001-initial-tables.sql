--liquibase formatted sql

--changeset divje:1

CREATE TABLE airport
(
    country VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    airport VARCHAR(255) PRIMARY KEY UNIQUE
);

CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE flight
(
    id             VARCHAR(255) NOT NULL PRIMARY KEY UNIQUE,
    from_airport   VARCHAR(255) NOT NULL,
    to_airport     VARCHAR(255) NOT NULL,
    carrier        VARCHAR(255) NOT NULL,
    departure_time TIMESTAMP    NOT NULL,
    arrival_time   TIMESTAMP    NOT NULL,
    CONSTRAINT flight_from_airport_fkey FOREIGN KEY (from_airport) REFERENCES airport (airport),
    CONSTRAINT flight_to_airport_fkey FOREIGN KEY (to_airport) REFERENCES airport (airport)
);