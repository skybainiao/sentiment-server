package sep3.sentiment;

import jakarta.persistence.NonUniqueResultException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import sep3.sentiment.model.User;
import sep3.sentiment.repository.UserRepository;
import sep3.sentiment.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Rollback(false)
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        assertEquals("test@example.com", createdUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }


    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(1L);

        assertTrue(retrievedUser.isPresent());
        assertEquals("testuser", retrievedUser.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByUsernameAndPasswordSuccess() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");

        when(userRepository.findByUsernameAndPassword("testuser", "password"))
                .thenReturn(Collections.singletonList(user));

        User foundUser = userService.findByUsernameAndPassword("testuser", "password");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        verify(userRepository, times(1)).findByUsernameAndPassword("testuser", "password");
    }
}
