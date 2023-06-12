package com.example.springblog.controller;

import com.example.springblog.model.User;
import com.example.springblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserService userService, @Qualifier("bCryptPasswordEncoder") BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@Valid User user,
                        BindingResult bindingResult,
                        Model model) {
        User existingUser = userService.findByUsername(user.getUsername()).orElse(null);


        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", true);
            return "redirect:/login";
        }
    }
}