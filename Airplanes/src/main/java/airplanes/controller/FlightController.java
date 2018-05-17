package airplanes.controller;

import airplanes.dto.FlightDTO;
import airplanes.entity.Airport;
import airplanes.service.AirportService;
import airplanes.service.FlightService;
import airplanes.service.RouteFinderServiceImpl;
import airplanes.service.route.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {

    private FlightService flightService;

    private AirportService airportService;

    private RouteFinderServiceImpl routeFinderService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService, RouteFinderServiceImpl routeFinderService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.routeFinderService = routeFinderService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createFlight(Model model) {
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flight", new FlightDTO());
        return "flight-form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createFlight(@ModelAttribute(value = "flight") FlightDTO flightDTO) {
        Integer departureAirportID = flightDTO.getDepartureAirportID();
        Date departureDate = Date.valueOf(flightDTO.getDepartureDate());
        LocalTime departureTime = LocalTime.parse(flightDTO.getDepartureTime());

        Integer arrivalAirportID = flightDTO.getArrivalAirportID();
        Date arrivalDate = Date.valueOf(flightDTO.getArrivalDate());
        LocalTime arrivalTime = LocalTime.parse(flightDTO.getArrivalTime());

        flightService.create(departureAirportID, arrivalAirportID, departureDate, arrivalDate, departureTime, arrivalTime);
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
        model.addAttribute("flight", new FlightDTO());
        return "route-form";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String route(Model model, @ModelAttribute(value="flight") FlightDTO flightDTO) {
        Airport departureAirport = airportService.findById(flightDTO.getDepartureAirportID());
        Airport arrivalAirport = airportService.findById(flightDTO.getArrivalAirportID());
        List<Route> routes = routeFinderService.findRoute(departureAirport, arrivalAirport);
        model.addAttribute("routes", routes);
        return "flights-list";
    }
}
