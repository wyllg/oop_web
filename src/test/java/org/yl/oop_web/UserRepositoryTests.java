package org.yl.oop_web;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void testSaveUser() {
        User user = new User();
        user.setEmail("testing@email.com");
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        user.setPassword("password123");
        user.setUsername("testuser1");
        user.setAddress("Libjo Central");

        User savedUser = userRepository.save(user);

        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals("testuser1", savedUser.getUsername());
    }

    @Test
    @Order(2)
    public void testFindUserByUsername() {
        Optional<User> userOpt = userRepository.findByUsername("testuser1");

        Assertions.assertTrue(userOpt.isPresent());
        Assertions.assertEquals("testuser1", userOpt.get().getUsername());
    }
}
