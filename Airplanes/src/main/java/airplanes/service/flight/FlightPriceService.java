package airplanes.service.flight;

import airplanes.entity.flight.Flight;
import airplanes.entity.FlightPrice;
import airplanes.entity.User;

public interface FlightPriceService {

    FlightPrice findByUserAndFlight(User user, Flight flight);

    FlightPrice update(User user, Flight flight);

}
