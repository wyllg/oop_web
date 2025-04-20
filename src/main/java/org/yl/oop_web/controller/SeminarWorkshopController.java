package org.yl.oop_web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.Application;
import org.yl.oop_web.model.SeminarWorkshop;
import org.yl.oop_web.service.ApplicationService;
import org.yl.oop_web.service.SeminarWorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/seminars") // Base mapping for seminars
public class SeminarWorkshopController {

    @Autowired
    private SeminarWorkshopService seminarWorkshopService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public String getAllSeminarsWorkshops(Model model) {
        List<SeminarWorkshop> seminarsWorkshops = seminarWorkshopService.getAllSeminarsWorkshops();
        model.addAttribute("seminarsWorkshops", seminarsWorkshops);
        return "seminars"; // returns the seminars.html template
    }

    @GetMapping("/add") // Mapping for adding a seminar/workshop
    public String showAddSeminarWorkshopForm(Model model) {
        model.addAttribute("seminarWorkshop", new SeminarWorkshop()); // For adding a new seminar/workshop
        return "addSeminarWorkshop"; // returns the addSeminarWorkshop.html template
    }

    @PostMapping
    public String addOrUpdateSeminarWorkshop(@ModelAttribute SeminarWorkshop seminarWorkshop) {
        seminarWorkshopService.addSeminarWorkshop(seminarWorkshop); // This will handle both add and update
        return "redirect:/seminars"; // Redirect to the seminars page after adding/updating
    }

    @GetMapping("/edit")
    public String showEditSeminarWorkshopForm(@RequestParam Long id, Model model) {
        SeminarWorkshop seminarWorkshop = seminarWorkshopService.getSeminarWorkshopById(id);
        model.addAttribute("seminarWorkshop", seminarWorkshop); // For updating an existing seminar/workshop
        return "addSeminarWorkshop"; // returns the addSeminarWorkshop.html template
    }

    @PostMapping("/apply") // New method to handle applications
    public String applyForSeminar(@RequestParam Long seminarId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SeminarWorkshop seminarWorkshop = seminarWorkshopService.getSeminarWorkshopById(seminarId);

        Application application = new Application();
        application.setUsername(username);
        application.setSeminarWorkshop(seminarWorkshop);

        applicationService.apply(application); // Save the application
        return "redirect:/seminars"; // Redirect to the seminars page after applying
    }
}