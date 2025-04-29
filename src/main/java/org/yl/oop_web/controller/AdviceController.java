package org.yl.oop_web.controller;

import org.yl.oop_web.model.Advice;
import org.yl.oop_web.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    // Show the form to submit advice
    @GetMapping
    public String showAdviceForm(Model model) {
        model.addAttribute("advice", new Advice()); // Empty advice form
        model.addAttribute("advices", adviceService.getAllAdvices()); // Show all advice submissions
        return "landing"; // Return the landing page
    }

    // Handle the form submission
    @PostMapping("/submit")
    public String submitAdvice(@ModelAttribute Advice advice) {
        adviceService.addAdvice(advice);
        return "redirect:/advice"; // Redirect to show the updated list of advice
    }
}
