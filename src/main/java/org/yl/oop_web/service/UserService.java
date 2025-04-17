// Java class that handles the logic related to saving user data and acts as a bridge between the controller and the repository

package org.yl.oop_web.service;

import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
