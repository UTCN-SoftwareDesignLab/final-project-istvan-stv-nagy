package airplanes.service.flight;

import airplanes.entity.Airplane;
import airplanes.entity.Airport;
import airplanes.entity.flight.Flight;
import airplanes.entity.Pilot;
import airplanes.entity.flight.FlightBuilder;
import airplanes.repository.AirplaneRepository;
import airplanes.repository.AirportRepository;
import airplanes.repository.FlightRepository;
import airplanes.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    private AirportRepository airportRepository;

    private AirplaneRepository airplaneRepository;

    private PilotRepository pilotRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, AirportRepository airportRepository, AirplaneRepository airplaneRepository, PilotRepository pilotRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airplaneRepository = airplaneRepository;
        this.pilotRepository = pilotRepository;
    }

    @Override
    public Flight create(Integer departureAirportID, Integer arrivalAirportID, Date departureDate, Date arrivalDate, LocalTime departureTime, LocalTime arrivalTime, double price, Integer airplaneID, Integer pilotID) {
        //find departure airport
        Airport departureAirport = airportRepository.findOne(departureAirportID);

        //find arrival airport
        Airport arrivalAirport = airportRepository.findOne(arrivalAirportID);

        //find airplane
        Airplane airplane = airplaneRepository.findOne(airplaneID);

        //find pilot
        Pilot pilot = pilotRepository.findOne(pilotID);

        //create flight
        Flight flight = new FlightBuilder()
                .setDepartureLocation(departureAirport)
                .setArrivalLocation(arrivalAirport)
                .setDepartureDate(departureDate)
                .setArrivalDate(arrivalDate)
                .setDepartureTime(departureTime)
                .setArrivalTime(arrivalTime)
                .setPrice(price)
                .setAirplane(airplane)
                .setPilot(pilot)
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

    @Override
    public List<Flight> findAllByPilot(Pilot pilot) {
        return flightRepository.findAllByPilot(pilot);
    }
}
