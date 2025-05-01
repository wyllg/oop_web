package org.yl.oop_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.Internship;
import org.yl.oop_web.service.InternshipService;

import java.util.List;

@Controller
@RequestMapping("/internships") // Base mapping for internships
public class InternshipController {

    @Autowired
    private InternshipService internshipService;

    @GetMapping
    public String getAllInternships(Model model) {
        List<Internship> internships = internshipService.getAllInternships();
        model.addAttribute("internships", internships);
        return "internships"; // returns internships.html
    }

    @GetMapping("/add") // Mapping for adding an internship
    public String showAddInternshipForm(Model model) {
        model.addAttribute("internship", new Internship()); // For adding a new internship
        return "addInternship"; // returns addInternship.html
    }

    @GetMapping("/edit") // Mapping for editing an existing internship
    public String showEditInternshipForm(@RequestParam Long id, Model model) {
        Internship internship = internshipService.getInternshipById(id);
        model.addAttribute("internship", internship); // For updating an existing internship
        return "addInternship"; // returns addInternship.html
    }

    @PostMapping // Handles both adding and updating internships
    public String addOrUpdateInternship(@ModelAttribute Internship internship) {
        internshipService.addInternship(internship); // This will handle both add and update
        return "redirect:/internships"; // Redirect to the internships page after adding/updating
    }
}
