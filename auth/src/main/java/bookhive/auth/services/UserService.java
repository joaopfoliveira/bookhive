package bookhive.auth.services;

import bookhive.auth.model.User;
import bookhive.auth.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
