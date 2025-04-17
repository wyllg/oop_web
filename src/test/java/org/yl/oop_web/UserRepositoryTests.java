package org.yl.oop_web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Test
    public void testFindByUsername() {
        String username = "wyellgaray";

        Optional<User> user = repo.findByUsername(username);

        assert user.isPresent();
    }
}
