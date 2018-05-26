package airplanes.service.flight;

import airplanes.entity.flight.Flight;
import airplanes.entity.FlightPrice;
import airplanes.entity.User;
import airplanes.repository.FlightPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightPriceServiceImpl implements FlightPriceService {

    private static final double PRICE_UP = 0.05;

    private FlightPriceRepository flightPriceRepository;

    @Autowired
    public FlightPriceServiceImpl(FlightPriceRepository flightPriceRepository) {
        this.flightPriceRepository = flightPriceRepository;
    }

    @Override
    public FlightPrice findByUserAndFlight(User user, Flight flight) {
        return flightPriceRepository.findByUserAndFlight(user, flight);
    }

    @Override
    public FlightPrice update(User user, Flight flight) {
        FlightPrice flightPrice = flightPriceRepository.findByUserAndFlight(user, flight);
        if (flightPrice == null) {
            flightPrice = new FlightPrice(user, flight, flight.getPrice());
        } else {
            flightPrice.setPrice(flightPrice.getPrice() * (1 + PRICE_UP));
        }
        flightPriceRepository.save(flightPrice);
        return flightPrice;
    }
}
