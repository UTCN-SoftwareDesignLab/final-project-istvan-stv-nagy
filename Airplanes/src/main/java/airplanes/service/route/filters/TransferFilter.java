package airplanes.service.route.filters;

import airplanes.service.route.Route;

import java.util.List;
import java.util.stream.Collectors;

public class TransferFilter implements Filter {

    private int maxStops;

    public TransferFilter(int maxStops) {
        this.maxStops = maxStops;
    }

    @Override
    public List<Route> applyFilter(List<Route> routes) {
        return routes.stream()
                .filter(p-> p.getFlights().size() - 1 <= maxStops)
                .collect(Collectors.toList());
    }
}
