package airplanes.service.ticket;

import airplanes.entity.Reservation;

public interface TicketGenerator {

    byte[] generateTicket(Reservation reservation);

}
