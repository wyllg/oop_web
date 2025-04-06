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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username).orElse(null);
//    }
}
