package org.yl.oop_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.Advice;
import org.yl.oop_web.service.AdviceService;

@Controller
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @GetMapping
    public String showAdvicePage(Model model) {
        model.addAttribute("advices", adviceService.getAllAdvices());
        model.addAttribute("advice", new Advice());
        return "advice";
    }

    @PostMapping("/submit")
    public String submitAdvice(@ModelAttribute Advice advice) {
        adviceService.saveAdvice(advice);
        return "redirect:/advice";
    }

    @PostMapping("/delete/{id}")
    public String deleteAdvice(@PathVariable Long id) {
        adviceService.deleteAdvice(id);
        return "redirect:/advice";
    }
}
