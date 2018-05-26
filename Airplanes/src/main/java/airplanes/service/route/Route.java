package airplanes.service.route;

import airplanes.entity.Airport;
import airplanes.entity.flight.Flight;

import java.util.LinkedList;
import java.util.List;

public class Route {

    private List<Flight> flights;

    private Airport departureAirport;
    private Airport arrivalAirport;

    private double totalPrice;

    public Route() {}

    public Route(Airport departureAirport, Airport arrivalAirport) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.flights = new LinkedList<>();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void add(Flight flight) {
        flights.add(0, flight);
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
