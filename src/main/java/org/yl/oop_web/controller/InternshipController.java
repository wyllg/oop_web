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

    @GetMapping("/add")
    public String showAddInternshipForm(Model model) {
        model.addAttribute("internship", new Internship());
        return "addInternship"; // returns addInternship.html
    }

    @GetMapping("/edit")
    public String showEditInternshipForm(@RequestParam Long id, Model model) {
        Internship internship = internshipService.getInternshipById(id);
        model.addAttribute("internship", internship);
        return "addInternship";
    }

    @PostMapping
    public String addOrUpdateInternship(@ModelAttribute Internship internship) {
        internshipService.addInternship(internship);
        return "redirect:/internships";
    }
}
