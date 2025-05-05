package org.yl.oop_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.Advice;
import org.yl.oop_web.model.User;
import org.yl.oop_web.repository.UserRepository;
import org.yl.oop_web.service.AdviceService;
import org.yl.oop_web.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/advice")
public class AdviceController {

    private final UserService userService;

    public AdviceController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @Autowired
    private AdviceService adviceService;

//    @GetMapping
//    public String showAdvicePage(Model model) {
//        model.addAttribute("advices", adviceService.getAllAdvices());
//        model.addAttribute("advice", new Advice());
//        return "advice";
//    }

    @GetMapping
    public String showAdvicePage(Model model, Principal principal) {
        // Always add advices to the model
        model.addAttribute("advices", adviceService.getAllAdvices());
        model.addAttribute("advice", new Advice());

        // Check if the user is logged in
        boolean isOwner = principal != null;
        model.addAttribute("isOwner", isOwner);

        return "advice";
    }

    @PostMapping("/submit")
    public String submitAdvice(@ModelAttribute Advice advice, Principal principal) {
        if (principal == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        adviceService.saveAdvice(advice);
        return "redirect:/advice";
    }

//    @PostMapping("/submit")
//    public String submitAdvice(@ModelAttribute Advice advice) {
//        adviceService.saveAdvice(advice);
//        return "redirect:/advice";
//    }

    @PostMapping("/delete/{id}")
    public String deleteAdvice(@PathVariable Long id) {
        adviceService.deleteAdvice(id);
        return "redirect:/advice";
    }
}
