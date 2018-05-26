package airplanes.controller;

import airplanes.entity.Airplane;
import airplanes.entity.Pilot;
import airplanes.entity.User;
import airplanes.entity.flight.Flight;
import airplanes.service.airplane.AirplaneService;
import airplanes.service.flight.FlightService;
import airplanes.service.pilot.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/pilot")
public class PilotController {

    private PilotService pilotService;

    private FlightService flightService;

    private AirplaneService airplaneService;

    @Autowired
    public PilotController(PilotService pilotService, FlightService flightService, AirplaneService airplaneService) {
        this.pilotService = pilotService;
        this.flightService = flightService;
        this.airplaneService = airplaneService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showProfile(HttpSession session, Model model) {
        User user = (User)session.getAttribute("active-user");
        model.addAttribute("user", user);
        return "pilot-profile";
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String showFlights(HttpSession session, Model model) {
        User user = (User)session.getAttribute("active-user");
        Pilot pilot = pilotService.findByUser(user);
        List<Flight> flights = flightService.findAllByPilot(pilot);
        model.addAttribute("flights", flights);
        return "pilot-flights";
    }

    @RequestMapping(value = "/airplanes", method = RequestMethod.GET)
    public String showAirplanes(Model model) {
        List<Airplane> airplanes = airplaneService.findAll();
        model.addAttribute("airplanes", airplanes);
        return "pilot-airplanes";
    }
}
