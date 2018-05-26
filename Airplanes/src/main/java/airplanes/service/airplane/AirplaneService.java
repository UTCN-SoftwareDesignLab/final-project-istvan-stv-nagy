package airplanes.service.airplane;

import airplanes.entity.Airplane;

import java.util.List;

public interface AirplaneService {

    List<Airplane> findAll();

    Airplane findById(Integer id);

}
