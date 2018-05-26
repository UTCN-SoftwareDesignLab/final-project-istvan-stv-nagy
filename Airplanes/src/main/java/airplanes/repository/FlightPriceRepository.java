package airplanes.repository;

import airplanes.entity.flight.Flight;
import airplanes.entity.FlightPrice;
import airplanes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightPriceRepository extends JpaRepository<FlightPrice, Integer> {

    FlightPrice findByUserAndFlight(User user, Flight flight);

}
