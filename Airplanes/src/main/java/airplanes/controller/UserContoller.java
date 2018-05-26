package airplanes.controller;

import airplanes.entity.Bookmark;
import airplanes.entity.FlightPrice;
import airplanes.entity.Reservation;
import airplanes.entity.User;
import airplanes.entity.flight.Flight;
import airplanes.service.bookmark.BookmarkService;
import airplanes.service.flight.FlightPriceService;
import airplanes.service.flight.FlightService;
import airplanes.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserContoller {

    private ReservationService reservationService;

    private BookmarkService bookmarkService;

    private FlightService flightService;

    private FlightPriceService flightPriceService;

    @Autowired
    public UserContoller(ReservationService reservationService, BookmarkService bookmarkService, FlightService flightService, FlightPriceService flightPriceService) {
        this.reservationService = reservationService;
        this.bookmarkService = bookmarkService;
        this.flightService = flightService;
        this.flightPriceService = flightPriceService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpSession session) {
        User user = (User)session.getAttribute("active-user");
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String flights(Model model, HttpSession session) {
        User user = (User)session.getAttribute("active-user");
        List<Reservation> reservations = reservationService.findAllByUser(user);
        model.addAttribute("reservations", reservations);
        return "reservation-list";
    }

    @RequestMapping(value = "/bookmark/{flightID}", method = RequestMethod.POST)
    public String bookmark(@PathVariable(value = "flightID") Integer flightID, HttpSession session) {
        Flight flight = flightService.findById(flightID);
        User user = (User)session.getAttribute("active-user");
        FlightPrice flightPrice = flightPriceService.findByUserAndFlight(user, flight);
        bookmarkService.create(user, flightPrice);
        return "redirect:/user/bookmarks";
    }

    @RequestMapping(value = "/bookmarks", method = RequestMethod.GET)
    public String showBookmarks(Model model, HttpSession session) {
        User user = (User)session.getAttribute("active-user");
        List<Bookmark> bookmarks = bookmarkService.findAllByUser(user);
        model.addAttribute("bookmarks", bookmarks);
        return "bookmark-list";
    }

}
