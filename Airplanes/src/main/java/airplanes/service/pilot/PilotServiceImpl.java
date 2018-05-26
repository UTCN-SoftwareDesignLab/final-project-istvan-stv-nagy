package airplanes.service.pilot;

import airplanes.entity.Pilot;
import airplanes.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotServiceImpl implements PilotService {

    private PilotRepository pilotRepository;

    @Autowired
    public PilotServiceImpl(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public List<Pilot> findAll() {
        return pilotRepository.findAll();
    }

    @Override
    public Pilot findById(Integer id) {
        return pilotRepository.findOne(id);
    }
}
