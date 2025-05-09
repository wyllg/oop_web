package org.yl.oop_web.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.Application;
import org.yl.oop_web.model.SeminarWorkshop;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.yl.oop_web.service.ApplicationService;
import org.yl.oop_web.service.SeminarWorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yl.oop_web.service.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/seminars") // Base mapping for seminars
public class SeminarWorkshopController {

    private final UserService userService;

    public SeminarWorkshopController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @Autowired
    private SeminarWorkshopService seminarWorkshopService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public String getAllSeminarsWorkshops(Model model, Principal principal) {
        List<SeminarWorkshop> seminarsWorkshops = seminarWorkshopService.getAllSeminarsWorkshops();
        model.addAttribute("seminarsWorkshops", seminarsWorkshops);

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

        return "seminars"; // returns the seminars.html template
    }

    @GetMapping("/add") // Mapping for adding a scholarship
    public String showAddSeminarWorkshopForm(Model model, Principal principal) {
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

        model.addAttribute("seminarWorkshop", new SeminarWorkshop()); // For adding a new seminar/workshop

        return "addSeminarWorkshop"; // returns the addScholarship.html template
    }

    @PostMapping
    public String addOrUpdateSeminarWorkshop(@ModelAttribute SeminarWorkshop seminarWorkshop) {
        seminarWorkshopService.addSeminarWorkshop(seminarWorkshop); // This will handle both add and update
        return "redirect:/seminars"; // Redirect to the seminars page after adding/updating
    }

    @PostMapping("/apply") // Method to handle applications
    public String applyForSeminar(@RequestParam Long seminarId, RedirectAttributes redirectAttributes) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SeminarWorkshop seminarWorkshop = seminarWorkshopService.getSeminarWorkshopById(seminarId);

        // Check if the seminar workshop exists
        if (seminarWorkshop == null) {
            redirectAttributes.addFlashAttribute("error", "Seminar/Workshop not found.");
            return "redirect:/seminars"; // Redirect to the seminars page with an error message
        }

        // Check if the user has already applied for this seminar/workshop
        if (applicationService.hasApplied(username, seminarId)) {
            redirectAttributes.addFlashAttribute("error", "You have already applied for this seminar/workshop.");
            return "redirect:/seminars"; // Redirect to the seminars page with an error message
        }

        Application application = new Application();
        application.setUsername(username);
        application.setSeminarWorkshop(seminarWorkshop);

        applicationService.apply(application); // Save the application
        return "redirect:/seminars"; // Redirect to the seminars page after applying
    }
}