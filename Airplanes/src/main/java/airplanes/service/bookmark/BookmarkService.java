package airplanes.service.bookmark;

import airplanes.entity.Bookmark;
import airplanes.entity.FlightPrice;
import airplanes.entity.User;

import java.util.List;

public interface BookmarkService {

    void create(User user, FlightPrice flight);

    List<Bookmark> findAllByUser(User user);
}
