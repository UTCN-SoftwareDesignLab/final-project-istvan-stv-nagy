package airplanes.service.airport;

import airplanes.entity.Airport;

import java.util.List;

public interface AirportService {

    List<Airport> findAll();

    Airport findById(Integer id);

}
