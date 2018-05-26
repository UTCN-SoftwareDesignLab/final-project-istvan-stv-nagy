package airplanes.service.route;

import airplanes.entity.Airport;
import airplanes.entity.flight.Flight;
import airplanes.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RouteFinder {

    @Autowired
    private FlightRepository flightRepository;

    public RouteFinder(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Route> findRoute(Airport departureAirport, Airport arrivalAirport, RouteCriteria criteria) {
        List<Route> routes = new ArrayList<>();

        PriorityQueue<State> states = new PriorityQueue<>(new StateComparator());

        //create start state
        State currentState = new State(departureAirport, 0);

        //add start state to queue
        states.add(currentState);

        //while we still have possible choices
        while (states.size() > 0) {
            //remove first element of the queue
            currentState = states.poll();

            //check if we arrived at the destination
            if (arrivedAtDestination(currentState, arrivalAirport)) {
                routes.add(computeRoute(currentState, departureAirport, arrivalAirport));
            }
            else {
                //generate successors
                List<Flight> possibleFlights = flightRepository.findAllByDepartureLocation(currentState.getAirport());
                for (Flight flight : possibleFlights) {
                    String arrivalLocation = flight.getArrivalLocation().getLocation();
                    if (validDestination(currentState, arrivalLocation)) {
                        states.add(buildState(currentState, flight, criteria));
                    }
                }
            }

        }

        return routes;
    }

    private Route computeRoute(State currentState, Airport departureAirport, Airport arrivalAirport) {
        Route route = new Route(departureAirport, arrivalAirport);

        while (currentState.getFlight() != null) {
            route.add(currentState.getFlight());
            currentState = currentState.getParent();
        }

        double totalPrice = 0;
        for (Flight flight : route.getFlights()) {
            totalPrice += flight.getPrice();
        }
        route.setTotalPrice(totalPrice);

        return route;
    }


    private State buildState(State currentState, Flight flight, RouteCriteria criteria) {
        Airport airport = flight.getArrivalLocation();
        int cost = currentState.getCost();
        switch (criteria) {
            case PRICE:
                cost += flight.getPrice();
            case TRANSFER:
                cost++;
                break;
        }
        return new State(airport, cost, currentState, flight);
    }

    private boolean arrivedAtDestination(State currentState, Airport arrivalAirport) {
        String currentLocation = currentState.getAirport().getLocation();
        String goalLocation = arrivalAirport.getLocation();
        return currentLocation.equals(goalLocation);
    }

    private boolean validDestination(State currentState, String arrivalLocation) {
        while (currentState != null) {
            if (currentState.getAirport().getLocation().equals(arrivalLocation))
                return false;
            currentState = currentState.getParent();
        }
        return true;
    }

}
