package airplanes.repository;

import airplanes.entity.Bookmark;
import airplanes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer>{

    List<Bookmark> findAllByUser(User user);

}
