package airplanes.service.pilot;

import airplanes.entity.Pilot;
import airplanes.entity.User;

import java.util.List;

public interface PilotService {

    List<Pilot> findAll();

    Pilot findById(Integer id);

    Pilot findByUser(User user);
}
