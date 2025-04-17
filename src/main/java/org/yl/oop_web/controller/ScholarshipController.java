package org.yl.oop_web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.yl.oop_web.model.Scholarship;
import org.yl.oop_web.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/scholarships")
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping
    public String getAllScholarships(Model model) {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        model.addAttribute("scholarships", scholarships);
        model.addAttribute("scholarship", new Scholarship()); // For the form
        return "scholarships"; // Return the scholarships.html view
    }

    @PostMapping
    public String addScholarship(Scholarship scholarship) {
        scholarshipService.addScholarship(scholarship);
        return "redirect:/scholarships"; // Redirect to the scholarships page after adding
    }
}