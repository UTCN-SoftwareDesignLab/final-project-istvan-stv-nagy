package airplanes.entity.flight;

import airplanes.entity.Airplane;
import airplanes.entity.Airport;
import airplanes.entity.Pilot;

import java.sql.Date;
import java.time.LocalTime;

public class FlightBuilder {

    private Flight flight;

    public FlightBuilder() {
        this.flight = new Flight();
    }

    public FlightBuilder setDepartureLocation(Airport airport) {
        flight.setDepartureLocation(airport);
        return this;
    }

    public FlightBuilder setArrivalLocation(Airport airport) {
        flight.setArrivalLocation(airport);
        return this;
    }

    public FlightBuilder setDepartureTime(LocalTime time) {
        flight.setDepartureTime(time);
        return this;
    }

    public FlightBuilder setArrivalTime(LocalTime time) {
        flight.setArrivalTime(time);
        return this;
    }

    public FlightBuilder setDepartureDate(Date date) {
        flight.setDepartureDate(date);
        return this;
    }

    public FlightBuilder setArrivalDate(Date date) {
        flight.setArrivalDate(date);
        return this;
    }

    public FlightBuilder setPrice(double price) {
        flight.setPrice(price);
        return this;
    }

    public FlightBuilder setAirplane(Airplane airplane) {
        flight.setAirplane(airplane);
        return this;
    }

    public FlightBuilder setPilot(Pilot pilot) {
        flight.setPilot(pilot);
        return this;
    }

    public FlightBuilder setPlacesLeft(int places) {
        flight.setPlacesLeft(places);
        return this;
    }

    public Flight build() {
        return flight;
    }

}
