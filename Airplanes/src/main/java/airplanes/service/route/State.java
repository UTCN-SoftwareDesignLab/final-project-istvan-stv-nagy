package airplanes.service.route;

import airplanes.entity.Airport;
import airplanes.entity.Flight;

public class State {

    private Airport airport;
    private int cost;
    private State parent;
    private Flight flight;

    public State(Airport airport, int cost) {
        this.airport = airport;
        this.cost = cost;
    }

    public State(Airport airport, int cost, State parent, Flight flight) {
        this.airport = airport;
        this.cost = cost;
        this.parent = parent;
        this.flight = flight;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
