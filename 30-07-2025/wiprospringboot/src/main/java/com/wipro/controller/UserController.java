package com.wipro.controller;

import com.wipro.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/userform")
    public ModelAndView showForm() {
        return new ModelAndView("userreg", "user", new User());
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("user") User user) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getUserEmail());
        System.out.println("Phone: " + user.getUserPhone());

        // Return back to the same page with a success message
        ModelAndView mav = new ModelAndView("userreg");
        mav.addObject("message", "User registered successfully!");
        return mav;
    }
}
