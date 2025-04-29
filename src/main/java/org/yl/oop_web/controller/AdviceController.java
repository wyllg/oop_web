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

    // Show the advice form and list of all advice
    @GetMapping
    public String showAdviceForm(Model model) {
        model.addAttribute("advice", new Advice()); // New advice object for the form
        model.addAttribute("advices", adviceService.getAllAdvices()); // List of all advice
        return "advice"; //
    }

    // Handle form submission
    @PostMapping("/submit")
    public String submitAdvice(@ModelAttribute Advice advice) {
        adviceService.addAdvice(advice);
        return "redirect:/advice"; // Refresh page to show new advice
    }
}
