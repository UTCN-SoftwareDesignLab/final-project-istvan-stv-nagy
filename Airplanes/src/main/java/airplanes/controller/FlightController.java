package airplanes.controller;

import airplanes.dto.FlightCreateDTO;
import airplanes.dto.FlightSearchDTO;
import airplanes.dto.RouteDTO;
import airplanes.entity.Airport;
import airplanes.entity.Bookmark;
import airplanes.entity.FlightPrice;
import airplanes.entity.flight.Flight;
import airplanes.entity.User;
import airplanes.service.airplane.AirplaneService;
import airplanes.service.airport.AirportService;
import airplanes.service.bookmark.BookmarkService;
import airplanes.service.flight.FlightPriceService;
import airplanes.service.flight.FlightService;
import airplanes.service.pilot.PilotService;
import airplanes.service.reservation.ReservationService;
import airplanes.service.route.Route;
import airplanes.service.route.RouteFinderService;
import airplanes.service.route.filters.Filter;
import airplanes.service.route.filters.PriceFilter;
import airplanes.service.route.filters.TransferFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {

    private FlightService flightService;

    private AirportService airportService;

    private AirplaneService airplaneService;

    private PilotService pilotService;

    private RouteFinderService routeFinderService;

    private ReservationService reservationService;

    private FlightPriceService flightPriceService;

    private BookmarkService bookmarkService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService, AirplaneService airplaneService, PilotService pilotService, RouteFinderService routeFinderService, ReservationService reservationService, FlightPriceService flightPriceService, BookmarkService bookmarkService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.airplaneService = airplaneService;
        this.pilotService = pilotService;
        this.routeFinderService = routeFinderService;
        this.reservationService = reservationService;
        this.flightPriceService = flightPriceService;
        this.bookmarkService = bookmarkService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createFlight(Model model) {
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("airplanes", airplaneService.findAll());
        model.addAttribute("pilots", pilotService.findAll());
        model.addAttribute("flight", new FlightCreateDTO());
        return "flight-form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createFlight(@ModelAttribute(value = "flight") FlightCreateDTO flightCreateDTO) {
        Integer departureAirportID = flightCreateDTO.getDepartureAirportID();
        Date departureDate = Date.valueOf(flightCreateDTO.getDepartureDate());
        LocalTime departureTime = LocalTime.parse(flightCreateDTO.getDepartureTime());

        Integer arrivalAirportID = flightCreateDTO.getArrivalAirportID();
        Date arrivalDate = Date.valueOf(flightCreateDTO.getArrivalDate());
        LocalTime arrivalTime = LocalTime.parse(flightCreateDTO.getArrivalTime());

        double price = flightCreateDTO.getPrice();

        Integer airplaneID = flightCreateDTO.getAirplaneID();

        Integer pilotID = flightCreateDTO.getPilotID();

        flightService.create(departureAirportID, arrivalAirportID, departureDate, arrivalDate, departureTime, arrivalTime, price, airplaneID, pilotID);
        return "redirect:/flight/findall";
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public String findAllFlights(Model model) {
        model.addAttribute("flights", flightService.findAll());
        return "flights-list";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flight", new FlightSearchDTO());
        return "route-form";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String route(Model model, @ModelAttribute(value="flight") FlightSearchDTO flightSearchDTO,
                             @RequestParam(value = "priceCheckbox", required = false) String priceFiltering,
                             @RequestParam(value = "transferCheckbox", required = false) String transferFiltering,
                             HttpSession session) {
        Airport departureAirport = airportService.findById(flightSearchDTO.getDepartureAirportID());
        Airport arrivalAirport = airportService.findById(flightSearchDTO.getArrivalAirportID());

        List<Route> routes = routeFinderService.findRoute(departureAirport, arrivalAirport);

        List<Filter> filters = new ArrayList<>();
        if (priceFiltering != null) filters.add(new PriceFilter(flightSearchDTO.getMinPrice(), flightSearchDTO.getMaxPrice()));
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
        reservationService.create(flight, user);
        return "redirect:/user/flights";
    }
}
