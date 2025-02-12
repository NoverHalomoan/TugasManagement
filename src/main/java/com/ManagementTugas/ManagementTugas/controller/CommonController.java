package com.ManagementTugas.ManagementTugas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ManagementTugas.ManagementTugas.DTOS.CreateUserDTO;
import com.ManagementTugas.ManagementTugas.model.LoginDTO;
import com.ManagementTugas.ManagementTugas.model.registerForm;

@Controller
public class CommonController {

    @GetMapping("/")
    public String awalLogin(Model model) {
        model.addAttribute("loginForm", new LoginDTO());
        return "login";
    }

    @GetMapping("/loginapp")
    public String tampilanLogin(Model model) {
        model.addAttribute("loginForm", new LoginDTO());
        return "login";
    }

    @GetMapping("/registerapp")
    public String tampilanregisterapp(Model model) {
        model.addAttribute("registerForm", new registerForm("", "", ""));
        return "register";
    }

    @GetMapping("/home")
    public String tampilanhome(Model model) {
        return "home";
    }

    @GetMapping("/portfolio")
    public String tampilanprofile(Model model) {
        return "profile";
    }

}
