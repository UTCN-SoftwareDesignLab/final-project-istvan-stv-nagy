package airplanes.service.user;

import airplanes.dto.UserDto;
import airplanes.entity.User;

import java.util.List;

public interface UserService {

    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();

    User create(UserDto userDto);

    void delete(Integer id);

    User findById(Integer id);

    User update(Integer id, UserDto userDto);

}
