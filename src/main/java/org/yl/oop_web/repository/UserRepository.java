// Java class that connects the user model to the rest of the application

package org.yl.oop_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yl.oop_web.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Used in AuthController to check for duplicate usernames
    Optional<User> findByEmail(String email); // Used in AuthController to check for duplicate emails
}
