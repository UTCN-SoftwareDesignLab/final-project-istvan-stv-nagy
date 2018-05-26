package airplanes.repository;

import airplanes.entity.Bookmark;
import airplanes.entity.FlightPrice;
import airplanes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer>{

    List<Bookmark> findAllByUser(User user);

    Bookmark findByUserAndFlight(User user, FlightPrice flight);

}
