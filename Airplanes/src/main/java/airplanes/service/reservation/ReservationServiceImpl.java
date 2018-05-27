package airplanes.service.reservation;

import airplanes.entity.flight.Flight;
import airplanes.entity.Reservation;
import airplanes.entity.User;
import airplanes.repository.FlightRepository;
import airplanes.repository.ReservationRepository;
import airplanes.service.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    private FlightRepository flightRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, FlightRepository flightRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public Reservation create(Flight flight, User user, double price) {
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Flight dbFlight = flightRepository.findOne(flight.getId());
        Reservation reservation = new Reservation(user, dbFlight, date, price);
        dbFlight.setPlacesLeft(dbFlight.getPlacesLeft() - 1);
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAllByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }

    @Override
    public Reservation findByID(Integer id) {
        return reservationRepository.findOne(id);
    }
}
