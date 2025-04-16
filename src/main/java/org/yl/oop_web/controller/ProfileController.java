package org.yl.oop_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.yl.oop_web.model.User;
import org.yl.oop_web.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
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
}
