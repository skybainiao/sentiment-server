package sep3.sentiment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sep3.sentiment.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameAndPassword(String username, String password);
}
