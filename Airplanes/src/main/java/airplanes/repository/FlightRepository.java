package airplanes.repository;

import airplanes.entity.Airport;
import airplanes.entity.Pilot;
import airplanes.entity.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAllByDepartureLocation(Airport departureLocation);

    List<Flight> findAllByPilot(Pilot pilot);

}
