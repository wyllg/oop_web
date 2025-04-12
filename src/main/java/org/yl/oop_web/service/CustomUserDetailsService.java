package org.yl.oop_web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yl.oop_web.model.CustomUserDetails;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

//    @Autowired
//    private UserService userService;
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetails(user.orElse(null));
    }

}
