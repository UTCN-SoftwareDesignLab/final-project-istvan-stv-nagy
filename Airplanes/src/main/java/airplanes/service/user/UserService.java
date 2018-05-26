package airplanes.service.user;

import airplanes.dto.UserDTO;
import airplanes.entity.User;

import java.util.List;

public interface UserService {

    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();

    User create(UserDTO userDto);

    void delete(Integer id);

    User findById(Integer id);

}
