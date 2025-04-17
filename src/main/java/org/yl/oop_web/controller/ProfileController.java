package org.yl.oop_web.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.yl.oop_web.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final UserRepository userRepository;

    public ProfileController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{username}")
    public String profileForm (@PathVariable String username, Model model, Principal principal) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            if (principal != null) {
                String loggedInUsername = principal.getName();
                model.addAttribute("isOwner", loggedInUsername.equals(username));
            } else {
                model.addAttribute("isOwner", false);
            }
        } else {
            // Handle cases where user is not found
            return "redirect:/login?error"; // or some error page
        }

        return "profile";

    }

    @GetMapping("/edit/{username}")
    public String editProfileForm (Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            // Handle cases where user is not found
            return "redirect:/login?error"; // or some error page
        }

        return "editprofile";
    }

    @PostMapping("/edit/{username}")
    public String editProfileSubmit(@PathVariable String username, @Valid User user, BindingResult result, Model model, Principal principal) {
        Optional<User> existingUserOpt = userService.findByUsername(username);

        if (existingUserOpt.isEmpty()) {
            return "test";
        }

        User existingUser = existingUserOpt.get();

        // Update the existing user's fields with the new values from the form
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setBio(user.getBio());
        existingUser.setBirthday(user.getBirthday());
        existingUser.setAddress(user.getAddress());
        existingUser.setContactNumber(user.getContactNumber());

        // Check for validation errors using Binding Result
        if (result.hasErrors()) {
            return "editprofile"; // Returns when there are errors present
        }

        // Check for duplicate email (excluding the current user)
        Optional<User> userWithEmail = userRepository.findByEmail(user.getEmail());
        if (userWithEmail.isPresent() && !userWithEmail.get().getId().equals(existingUser.getId())) {
            result.rejectValue("email", "error.user", "Email is already registered");
            return "editprofile";
        }

        // Save the updated user using UserService
        userService.saveUser(existingUser);
        return "redirect:/profile/" + username; // Redirect to the updated profile

    }
}
