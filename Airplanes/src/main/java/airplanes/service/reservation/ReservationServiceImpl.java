package airplanes.service.reservation;

import airplanes.entity.flight.Flight;
import airplanes.entity.Reservation;
import airplanes.entity.User;
import airplanes.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation create(Flight flight, User user, double price) {
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        Reservation reservation = new Reservation(user, flight, date, price);
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
