package airplanes.service;

import airplanes.entity.Airport;
import airplanes.entity.Flight;
import airplanes.entity.builder.FlightBuilder;
import airplanes.repository.AirportRepository;
import airplanes.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    private AirportRepository airportRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Flight create(Integer departureAirportID, Integer arrivalAirportID, Date departureDate, Date arrivalDate, LocalTime departureTime, LocalTime arrivalTime) {
        //find departure airport
        Airport departureAirport = airportRepository.findOne(departureAirportID);

        //find arrival airport
        Airport arrivalAirport = airportRepository.findOne(arrivalAirportID);

        //create flight
        Flight flight = new FlightBuilder()
                .setDepartureLocation(departureAirport)
                .setArrivalLocation(arrivalAirport)
                .setDepartureDate(departureDate)
                .setArrivalDate(arrivalDate)
                .setDepartureTime(departureTime)
                .setArrivalTime(arrivalTime)
                .build();

        //save flight
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findAllFrom(Airport airport) {
        return flightRepository.findAllByDepartureLocation(airport);
    }

    @Override
    public Flight findById(Integer id) {
        return flightRepository.findOne(id);
    }
}
