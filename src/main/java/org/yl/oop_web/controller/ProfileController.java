package org.yl.oop_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yl.oop_web.model.User;
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


}
