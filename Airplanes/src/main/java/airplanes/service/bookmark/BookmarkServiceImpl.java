package airplanes.service.bookmark;

import airplanes.entity.Bookmark;
import airplanes.entity.FlightPrice;
import airplanes.entity.User;
import airplanes.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public void create(User user, FlightPrice flight) {
        Bookmark bookmark = new Bookmark(user, flight);
        bookmarkRepository.save(bookmark);
    }

    @Override
    public List<Bookmark> findAllByUser(User user) {
        return bookmarkRepository.findAllByUser(user);
    }
}
