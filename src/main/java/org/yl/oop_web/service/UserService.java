// Java class that handles the logic related to saving user data and acts as a bridge between the controller and the repository

package org.yl.oop_web.service;

import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
