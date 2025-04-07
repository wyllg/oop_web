package org.yl.oop_web.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.yl.oop_web.model.User;
import org.yl.oop_web.service.UserService;
import org.yl.oop_web.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Injects BCryptPasswordEncoder into AuthController

    public AuthController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup") // Displays the signup form
    public String signupForm(Model model) {
        model.addAttribute("user", new User()); // Adds a new user to the model
        return "signup";
    }

    @PostMapping("/signup") // Handles submission of the signup form
    public String signupSubmit(@Valid User user, BindingResult result, Model model) { // Validates user using @Valid annotation

        // Check for validation errors using Binding Result
        if (result.hasErrors()) {
            return "signup"; // Returns to signup when there are errors present
        }

        // Check for duplicate username using UserRepository if a duplicate is found, it adds an error to Binding Result
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            result.rejectValue("username", "error.user", "Username is already taken");
            return "signup";
        }

        // Check for duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Email is already registered");
            return "signup";
        }

        // Update the signupSubmit method to hash the user's password before saving it
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Save the user if no duplicates are found using UserService
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}