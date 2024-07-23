package sep3.sentiment.service;

import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3.sentiment.model.User;
import sep3.sentiment.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsernameAndPassword(String username, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(username, password);
        if (users.size() == 1) {
            return users.get(0);
        } else if (users.isEmpty()) {
            return null;
        } else {
            throw new NonUniqueResultException("Query did not return a unique result: " + users.size() + " results were returned");
        }
    }
}
