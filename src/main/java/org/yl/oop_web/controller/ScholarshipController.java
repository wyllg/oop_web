package org.yl.oop_web.controller;

import org.yl.oop_web.model.Scholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.yl.oop_web.service.ScholarshipService;

import java.util.List;

@Controller
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping("/scholarships")
    public String scholarships(Model model) {
        List<Scholarship> scholarshipList = scholarshipService.getAllScholarships();
        model.addAttribute("scholarships", scholarshipList);
        return "scholarships"; // This refers to the scholarships.html template
    }
}