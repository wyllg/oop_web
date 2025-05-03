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
    public String showAdvicePage(Model model, @RequestParam(required = false) String search) {
        List<Advice> advices = (search != null) ? adviceService.searchAdvicesByHashtag(search) : adviceService.getAllAdvices();
        model.addAttribute("advices", advices);
        model.addAttribute("advice", new Advice());
        return "advice";
    }

    // Other methods remain unchanged
}