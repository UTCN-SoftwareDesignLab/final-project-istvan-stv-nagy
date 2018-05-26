package airplanes.repository;

import airplanes.entity.Pilot;
import airplanes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Integer> {

    Pilot findByUser(User user);

}
