package sep3.sentiment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sep3.sentiment.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
