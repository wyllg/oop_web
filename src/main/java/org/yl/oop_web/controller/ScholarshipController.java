package org.yl.oop_web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.yl.oop_web.model.Scholarship;
import org.yl.oop_web.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/scholarships") // Base mapping updated to /scholarships
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping
    public String getAllScholarships(Model model) {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        model.addAttribute("scholarships", scholarships);
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
    public String addOrUpdateScholarship(Scholarship scholarship) {
        scholarshipService.addScholarship(scholarship); // This will handle both add and update
        return "redirect:/scholarships"; // Redirect to the scholarships page after adding/updating
    }
}