package airplanes.controller;

import airplanes.dto.FlightCreateDTO;
import airplanes.dto.UserDTO;
import airplanes.entity.User;
import airplanes.service.airplane.AirplaneService;
import airplanes.service.airport.AirportService;
import airplanes.service.flight.FlightService;
import airplanes.service.pilot.PilotService;
import airplanes.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalTime;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AirportService airportService;
    private AirplaneService airplaneService;
    private PilotService pilotService;
    private FlightService flightService;
    private UserService userService;

    @Autowired
    public AdminController(AirportService airportService, AirplaneService airplaneService, PilotService pilotService, FlightService flightService, UserService userService) {
        this.airportService = airportService;
        this.airplaneService = airplaneService;
        this.pilotService = pilotService;
        this.flightService = flightService;
        this.userService = userService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfile(HttpSession session, Model model) {
        User user = (User)session.getAttribute("active-user");
        model.addAttribute("user", user);
        return "admin-profile";
    }

    @RequestMapping(value = "/flight/create", method = RequestMethod.GET)
    public String createFlight(Model model) {
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("airplanes", airplaneService.findAll());
        model.addAttribute("pilots", pilotService.findAll());
        model.addAttribute("flight", new FlightCreateDTO());
        return "flight-form";
    }

    @RequestMapping(value = "/flight/create", method = RequestMethod.POST)
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
        return "redirect:/admin/flight/findall";
    }

    @RequestMapping(value = "/flight/findall", method = RequestMethod.GET)
    public String findAllFlights(Model model) {
        model.addAttribute("flights", flightService.findAll());
        return "flights-list";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user-form";
    }

    @RequestMapping(value = "user/create", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("user") UserDTO userDto) {
        userService.create(userDto);
        return "redirect:/admin/user/findall";
    }

    @RequestMapping(value = "/user/findall", method = RequestMethod.GET)
    public String findAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users-list";
    }

}
