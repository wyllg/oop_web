package org.yl.oop_web.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.yl.oop_web.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
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

    @GetMapping("/{username}/edit")
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

    @PostMapping("/{username}/edit")
    public String editProfileSubmit(@ModelAttribute("user") User updatedUser, BindingResult result, Principal principal) {
        String username = principal.getName();
        User existingUser = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!principal.getName().equals(username)) {
            return "redirect:/access-denied"; // or show error page
        }

        // Check for duplicate email (excluding the current user's email)
        if (!updatedUser.getEmail().equals(existingUser.getEmail()) &&
                userService.findByEmail(updatedUser.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Email is already registered");
            return "editprofile"; // Return to the edit profile page with an error message
        }

        // Update required fields (they are already validated by @NotBlank)
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());

        // Update optional fields
        // If the field is blank or null in the form, set it to null in the database
        existingUser.setBio(updatedUser.getBio() != null && !updatedUser.getBio().isEmpty() ? updatedUser.getBio() : null);
        existingUser.setBirthday(updatedUser.getBirthday()); // Birthday can be null
        existingUser.setAddress(updatedUser.getAddress() != null && !updatedUser.getAddress().isEmpty() ? updatedUser.getAddress() : null);
        existingUser.setContactNumber(updatedUser.getContactNumber() != null && !updatedUser.getContactNumber().isEmpty() ? updatedUser.getContactNumber() : null);

        userService.saveUser(existingUser);

        return "redirect:/profile/{username}";
    }


}
