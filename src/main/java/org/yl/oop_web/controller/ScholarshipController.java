package org.yl.oop_web.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.Scholarship;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.yl.oop_web.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.yl.oop_web.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/scholarships") // Base mapping updated to /scholarships
public class ScholarshipController {

    private final UserService userService;

    public ScholarshipController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping
    public String getAllScholarships(Model model, Principal principal) {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        model.addAttribute("scholarships", scholarships);

        if (principal != null) {
            String username = principal.getName();
            Optional<User> user = userService.findByUsername(username);

            if (user.isPresent()) {
                model.addAttribute("isOwner", true);
                model.addAttribute("user", user.get()); // Assuming you have a User class
            } else {
                model.addAttribute("isOwner", false);
            }
        } else {
            model.addAttribute("isOwner", false);
        }

        return "scholarships"; // returns the scholarships.html template
    }

    @GetMapping("/add") // Mapping for adding a scholarship
    public String showAddScholarshipForm(Model model) {
        model.addAttribute("scholarship", new Scholarship()); // For adding a new scholarship
        return "addScholarship"; // returns the addScholarship.html template
    }

    @GetMapping("/edit")
    public String showEditScholarshipForm(@RequestParam Long id, Model model) {
        Scholarship scholarship = scholarshipService.getScholarshipById(id);
        model.addAttribute("scholarship", scholarship); // For updating an existing scholarship
        return "addScholarship"; // returns the addScholarship.html template
    }

    @PostMapping
    public String addOrUpdateScholarship(@ModelAttribute Scholarship scholarship) {
        // No need to handle image upload, just save the scholarship with the image link
        scholarshipService.addScholarship(scholarship); // This will handle both add and update
        return "redirect:/scholarships"; // Redirect to the scholarships page after adding/updating
    }
}