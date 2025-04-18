package org.yl.oop_web.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yl.oop_web.model.User;
import org.yl.oop_web.service.UserService;

import java.security.Principal;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("months")
    public List<String> getMonths() {
        List<String> months = new ArrayList<>();
        months.add(""); // Add an empty option
        months.addAll(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
        return months;
    }

    @ModelAttribute("years")
    public List<Integer> getYears() {
        List<Integer> years = new ArrayList<>();
        years.add(null); // Add a null option
        years.addAll(Arrays.asList(1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033, 2034, 2035));
        return years;
    }

    public static String formatMonth(Month month) {
        if (month == null) {
            return "";
        }
        return month.toString().charAt(0) + month.toString().substring(1).toLowerCase();
    }

//    @ModelAttribute("months")
//    public List<String> getMonths() {
//        return Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
//    }
//
//    @ModelAttribute("years")
//    public List<Integer> getYears() {
//        return Arrays.asList(1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019,2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033, 2034, 2035);  // Customize the year range as needed
//    }

    @GetMapping("/{username}")
    public String profileForm (@PathVariable String username, Model model, Principal principal) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            if (principal != null) {
                String loggedInUsername = principal.getName();
                model.addAttribute("isOwner", loggedInUsername.equals(username));
            } else {
                model.addAttribute("isOwner", false);
            }
        } else {
            // Handle cases where user is not found
            return "redirect:/login?error"; // or some error page
        }

        return "profile";

    }

    @GetMapping("/{username}/edit")
    public String editProfileForm (Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        } else {
            // Handle cases where user is not found
            return "redirect:/login?error"; // or some error page
        }

        return "editprofile";
    }

    @PostMapping("/{username}/edit")
    public String editProfileSubmit(@ModelAttribute("user") User updatedUser, BindingResult result, Principal principal) {
        String username = principal.getName();
        User existingUser = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!principal.getName().equals(username)) {
            return "redirect:/access-denied"; // or show error page
        }

        // Check for duplicate email (excluding the current user's email)
        if (!updatedUser.getEmail().equals(existingUser.getEmail()) &&
                userService.findByEmail(updatedUser.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Email is already registered");
            return "editprofile"; // Return to the edit profile page with an error message
        }



        // Update required fields (they are already validated by @NotBlank)
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());

        // Update optional fields
        // If the field is blank or null in the form, set it to null in the database
        existingUser.setBio(updatedUser.getBio() != null && !updatedUser.getBio().isEmpty() ? updatedUser.getBio() : null);
        existingUser.setBirthday(updatedUser.getBirthday() != null ? updatedUser.getBirthday() : null); // Birthday can be null
        existingUser.setAddress(updatedUser.getAddress() != null && !updatedUser.getAddress().isEmpty() ? updatedUser.getAddress() : null);
        existingUser.setContactNumber(updatedUser.getContactNumber() != null && !updatedUser.getContactNumber().isEmpty() ? updatedUser.getContactNumber() : null);
        existingUser.setProfilePicUrl(updatedUser.getProfilePicUrl() != null &&!updatedUser.getProfilePicUrl().isEmpty() ? updatedUser.getProfilePicUrl() : null);

        //Education
        existingUser.setSchool(updatedUser.getSchool() != null && !updatedUser.getSchool().isEmpty() ? updatedUser.getSchool() : null);
        existingUser.setDegree(updatedUser.getDegree() != null && !updatedUser.getDegree().isEmpty() ? updatedUser.getDegree() : null);
        existingUser.setFieldOfStudy(updatedUser.getFieldOfStudy() != null && !updatedUser.getFieldOfStudy().isEmpty() ? updatedUser.getFieldOfStudy() : null);
        existingUser.setStartMonth(updatedUser.getStartMonth() != null ? updatedUser.getStartMonth() : null);
        existingUser.setStartYear(updatedUser.getStartYear() != null ? updatedUser.getStartYear() : null);
        existingUser.setEndMonth(updatedUser.getEndMonth() != null ? updatedUser.getEndMonth() : null);
        existingUser.setEndYear(updatedUser.getEndYear() != null ? updatedUser.getEndYear() : null);

        userService.saveUser(existingUser);

        return "redirect:/profile/{username}";
    }


}
