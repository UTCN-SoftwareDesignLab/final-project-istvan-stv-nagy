package airplanes.controller;

import airplanes.dto.FlightSearchDTO;
import airplanes.dto.RouteDTO;
import airplanes.entity.Airport;
import airplanes.entity.FlightPrice;
import airplanes.entity.User;
import airplanes.entity.flight.Flight;
import airplanes.service.airplane.AirplaneService;
import airplanes.service.airport.AirportService;
import airplanes.service.bookmark.BookmarkService;
import airplanes.service.flight.FlightPriceService;
import airplanes.service.flight.FlightService;
import airplanes.service.pilot.PilotService;
import airplanes.service.reservation.ReservationService;
import airplanes.service.route.Route;
import airplanes.service.route.RouteCriteria;
import airplanes.service.route.RouteFinderService;
import airplanes.service.route.filters.Filter;
import airplanes.service.route.filters.PriceFilter;
import airplanes.service.route.filters.TransferFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {

    private FlightService flightService;

    private AirportService airportService;

    private RouteFinderService routeFinderService;

    private ReservationService reservationService;

    private FlightPriceService flightPriceService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService, RouteFinderService routeFinderService, ReservationService reservationService, FlightPriceService flightPriceService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.routeFinderService = routeFinderService;
        this.reservationService = reservationService;
        this.flightPriceService = flightPriceService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flight", new FlightSearchDTO());
        return "route-form";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String route(Model model, @ModelAttribute(value="flight") FlightSearchDTO flightSearchDTO,
                             @RequestParam(value = "gridCheck1", required = false) String priceFiltering,
                             @RequestParam(value = "gridCheck2", required = false) String transferFiltering,
                             @RequestParam(value = "criteriaID", required = false) String orderingCriteria,
                             HttpSession session) {
        Airport departureAirport = airportService.findById(flightSearchDTO.getDepartureAirportID());
        Airport arrivalAirport = airportService.findById(flightSearchDTO.getArrivalAirportID());

        RouteCriteria routeCriteria = RouteCriteria.TRANSFER;
        if (orderingCriteria == null)
            routeCriteria = RouteCriteria.TRANSFER;
        else if (orderingCriteria.equals("price"))
            routeCriteria = RouteCriteria.PRICE;
        else if (orderingCriteria.equals("transfer"))
            routeCriteria = RouteCriteria.TRANSFER;
        List<Route> routes = routeFinderService.findRoute(departureAirport, arrivalAirport, routeCriteria);

        List<Filter> filters = new ArrayList<>();
        if (priceFiltering != null) filters.add(new PriceFilter(flightSearchDTO.getMaxPrice()));
        if (transferFiltering != null) filters.add(new TransferFilter(flightSearchDTO.getMaxTransfers()));

        for (Filter filter : filters) {
            routes = filter.applyFilter(routes);
        }

        User user = (User)session.getAttribute("active-user");

        List<Flight> distinctFlights = new ArrayList<>();

        List<RouteDTO> routeDTOs = new ArrayList<>();
        for (Route r : routes) {
            RouteDTO routeDTO = new RouteDTO(r.getDepartureAirport(), r.getArrivalAirport());
            double totalPrice = 0;
            for (Flight f : r.getFlights()) {
                FlightPrice flightPrice;
                if (!distinctFlights.contains(f)) {
                    distinctFlights.add(f);
                    flightPrice = flightPriceService.update(user, f);
                } else {
                    flightPrice = flightPriceService.findByUserAndFlight(user, f);
                }
                totalPrice += flightPrice.getPrice();
                routeDTO.add(flightPrice);
            }
            routeDTO.setTotalPrice(totalPrice);
            if (priceFiltering == null || routeCriteria != RouteCriteria.PRICE || totalPrice <= flightSearchDTO.getMaxPrice())
                routeDTOs.add(routeDTO);
        }

        model.addAttribute("routes", routeDTOs);
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flight", flightSearchDTO);

        return "route-form";
    }

    @RequestMapping(value = "/book/{flightID}", method = RequestMethod.POST)
    public String buyTicket(@PathVariable(value = "flightID") Integer flightID, HttpSession session) {
        Flight flight = flightService.findById(flightID);
        User user = (User)session.getAttribute("active-user");
        FlightPrice flightPrice = flightPriceService.findByUserAndFlight(user, flight);
        reservationService.create(flight, user, flightPrice.getPrice());
        return "redirect:/user/flights";
    }
}
