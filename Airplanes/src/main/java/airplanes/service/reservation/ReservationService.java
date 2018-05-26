package airplanes.service.reservation;

import airplanes.entity.flight.Flight;
import airplanes.entity.Reservation;
import airplanes.entity.User;

import java.util.List;

public interface ReservationService {

    Reservation create(Flight flight, User user, double price);

    List<Reservation> findAllByUser(User user);

    Reservation findByID(Integer id);

}
