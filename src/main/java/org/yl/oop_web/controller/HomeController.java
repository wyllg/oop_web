package org.yl.oop_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Sets the url
    public String home() {
        return "home"; // Returns the home.html file view
    }

}
