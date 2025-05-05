package org.yl.oop_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.Internship;
import org.yl.oop_web.model.Scholarship;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.yl.oop_web.service.InternshipService;
import org.yl.oop_web.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/internships") // Base mapping for internships
public class InternshipController {

    private final UserService userService;

    public InternshipController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @Autowired
    private InternshipService internshipService;

    @GetMapping
    public String getAllInternships(Model model) {
        List<Internship> internships = internshipService.getAllInternships();
        model.addAttribute("internships", internships);
        return "internships"; // returns internships.html
    }

    @GetMapping("/add") // Mapping for adding a scholarship
    public String showAddInternshipForm(Model model, Principal principal) {
        // Check if the user is logged in
        if (principal == null) {
            // Redirect to homepage if the user is not logged in
            return "redirect:/";
        }

        String username = principal.getName();
        Optional<User> userOptional = userService.findByUsername(username);

        // Check if the user exists
        if (!userOptional.isPresent()) {
            // Redirect to homepage if user is not found
            return "redirect:/";
        }

        User user = userOptional.get(); // Get the User object
        String role = user.getRole(); // Retrieve the user's role

        // Check if role is null or empty
        if (role == null || role.trim().isEmpty()) {
            // Redirect to homepage if role is null or empty
            return "redirect:/landing";
        }

        model.addAttribute("internship", new Internship()); // For adding a new internship

        return "addInternship"; // returns addInternship.html
    }

    @PostMapping // Handles both adding and updating internships
    public String addOrUpdateInternship(@ModelAttribute Internship internship) {
        internshipService.addInternship(internship); // This will handle both add and update
        return "redirect:/internships"; // Redirect to the internships page after adding/updating
    }
}
