package airplanes.dto;

import airplanes.entity.Airport;
import airplanes.entity.FlightPrice;

import java.util.LinkedList;
import java.util.List;

public class RouteDTO {

    private List<FlightPrice> flights;

    private Airport departureAirport;
    private Airport arrivalAirport;

    private double totalPrice;

    public RouteDTO() {}

    public RouteDTO(Airport departureAirport, Airport arrivalAirport) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.flights = new LinkedList<>();
    }

    public void add(FlightPrice flightPrice) {
        flights.add(0, flightPrice);
    }

    public List<FlightPrice> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightPrice> flights) {
        this.flights = flights;
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
