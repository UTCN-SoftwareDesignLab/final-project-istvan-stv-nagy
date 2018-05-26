package airplanes.service.flight;

import airplanes.entity.Airport;
import airplanes.entity.flight.Flight;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public interface FlightService {

    Flight create(Integer departureAirportID, Integer arrivalAirportID, Date departureDate, Date arrivalDate, LocalTime departureTime, LocalTime arrivalTime, double price, Integer airplaneID, Integer pilotID);

    List<Flight> findAll();

    List<Flight> findAllFrom(Airport airport);

    Flight findById(Integer id);
}
