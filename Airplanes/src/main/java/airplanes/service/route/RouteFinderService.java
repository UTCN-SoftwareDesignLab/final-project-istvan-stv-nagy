package airplanes.service.route;

import airplanes.entity.Airport;
import airplanes.service.route.Route;

import java.util.List;

public interface RouteFinderService {

    List<Route> findRoute(Airport departureAirport, Airport arrivalAirport, RouteCriteria criteria);

}
