package com.mentorassignment.RetailApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // This corresponds to login.jsp
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password, Model model) {
        // Your authentication logic here
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "redirect:/home"; // Redirect to home page if login is successful
        } else {
            model.addAttribute("errorMessage", "Invalid credentials. Please try again.");
            return "login"; // Return to login.jsp with error message
        }
    }
}
