package airplanes.service.user;

import airplanes.dto.UserDTO;
import airplanes.entity.User;
import airplanes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDto) {
        String password = new ShaPasswordEncoder().encodePassword(userDto.getPassword(), "SHA-1");
        User user = new User(userDto.getUsername(), password, userDto.getRole(), userDto.getFirstName(), userDto.getLastName());
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findOne(id);
    }
}
