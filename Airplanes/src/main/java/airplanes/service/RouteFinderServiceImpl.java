package airplanes.service;

import airplanes.entity.Airport;
import airplanes.service.route.Route;
import airplanes.service.route.RouteFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteFinderServiceImpl implements RouteFinderService {

    private RouteFinder routeFinder;

    @Autowired
    public RouteFinderServiceImpl(RouteFinder routeFinder) {
        this.routeFinder = routeFinder;
    }

    @Override
    public List<Route> findRoute(Airport departureAirport, Airport arrivalAirport) {
        return routeFinder.findRoute(departureAirport, arrivalAirport);
    }
}
