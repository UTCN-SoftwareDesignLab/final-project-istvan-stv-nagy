package airplanes.service.route.filters;

import airplanes.service.route.Route;

import java.util.List;

public interface Filter {

    List<Route> applyFilter(List<Route> routes);

}
