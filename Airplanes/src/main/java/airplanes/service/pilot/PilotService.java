package airplanes.service.pilot;

import airplanes.entity.Pilot;

import java.util.List;

public interface PilotService {

    List<Pilot> findAll();

    Pilot findById(Integer id);
}
