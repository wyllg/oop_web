package org.yl.oop_web.controller;

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

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid User user, BindingResult result, Model model) {
        // Check for validation errors
        if (result.hasErrors()) {
            return "signup";
        }

        // Check for duplicate username
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            result.rejectValue("username", "error.user", "Username is already taken");
            return "signup";
        }

        // Check for duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Email is already registered");
            return "signup";
        }

        // Save the user if no duplicates are found
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}