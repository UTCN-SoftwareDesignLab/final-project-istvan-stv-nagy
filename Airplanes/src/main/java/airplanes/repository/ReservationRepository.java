package airplanes.repository;

import airplanes.entity.Reservation;
import airplanes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByUser(User user);

}
