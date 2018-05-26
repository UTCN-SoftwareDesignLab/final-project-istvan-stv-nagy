package airplanes.controller;

import airplanes.entity.Reservation;
import airplanes.service.reservation.ReservationService;
import airplanes.service.ticket.TicketGeneratorPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TicketController {

    private TicketGeneratorPDF ticketGeneratorPDF;

    private ReservationService reservationService;

    @Autowired
    public TicketController(TicketGeneratorPDF ticketGeneratorPDF, ReservationService reservationService) {
        this.ticketGeneratorPDF = ticketGeneratorPDF;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/ticket/download/{reservationID}", method = RequestMethod.POST)
    public ResponseEntity<byte[]> generateTicket(@PathVariable(value="reservationID") Integer reservationID) {
        Reservation reservation = reservationService.findByID(reservationID);
        byte[] ticket = ticketGeneratorPDF.generateTicket(reservation);
        return new ResponseEntity<>(ticket, null, HttpStatus.OK);
    }
}
