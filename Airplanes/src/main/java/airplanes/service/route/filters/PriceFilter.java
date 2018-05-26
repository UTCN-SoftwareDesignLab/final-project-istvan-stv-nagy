package airplanes.service.route.filters;

import airplanes.service.route.Route;

import java.util.List;
import java.util.stream.Collectors;

public class PriceFilter implements Filter {

    private double maxPrice;

    public PriceFilter(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public List<Route> applyFilter(List<Route> routes) {
        return routes.stream()
                .filter(p -> p.getTotalPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
