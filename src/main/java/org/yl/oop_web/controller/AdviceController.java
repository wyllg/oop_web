package org.yl.oop_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.Advice;
import org.yl.oop_web.service.AdviceService;

import java.util.List;

@Controller
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @GetMapping
    public String showAdvicePage(Model model) {
        List<Advice> advices = adviceService.getAllAdvices();
        model.addAttribute("advices", advices);
        model.addAttribute("advice", new Advice());
        return "advice";
    }

    @PostMapping("/submit")
    public String submitAdvice(@ModelAttribute Advice advice) {
        adviceService.addAdvice(advice);
        return "redirect:/advice";
    }

    @PostMapping("/delete/{id}")
    public String deleteAdvice(@PathVariable Long id) {
        adviceService.deleteAdviceById(id);
        return "redirect:/advice";
    }
}
