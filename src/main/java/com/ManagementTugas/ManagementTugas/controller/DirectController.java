package com.ManagementTugas.ManagementTugas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ManagementTugas.ManagementTugas.DTOS.CreateUserDTO;
import com.ManagementTugas.ManagementTugas.model.LoginDTO;
import com.ManagementTugas.ManagementTugas.model.Users;
import com.ManagementTugas.ManagementTugas.model.registerForm;
import com.ManagementTugas.ManagementTugas.service.ServiceUser;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class DirectController {

    private final UsersController usersController;
    private final ServiceUser serviceUser;

    private static ApiResponseData<Object> apiResponseData = new ApiResponseData<>();

    public DirectController(UsersController usersController, ServiceUser serviceUser) {
        this.usersController = usersController;
        this.serviceUser = serviceUser;
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/loginapp")
    public String hasillogin(@Valid @ModelAttribute("loginForm") LoginDTO loginDTO, BindingResult bindingResult,
            HttpServletResponse response, org.springframework.ui.Model model) {

        if (bindingResult.hasErrors()) {
            List<String> errorsmessage = bindingResult.getAllErrors().stream().map(
                    errors -> errors.getDefaultMessage()).collect(Collectors.toList());

            model.addAttribute("loginerror", errorsmessage);
            return "login";
        }

        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            ResponseEntity<?> entityres = usersController.loginaplication(headers, loginDTO, response);

            apiResponseData = (ApiResponseData<Object>) entityres.getBody();
            // System.out.println(apiResponseData.getStatus());
            if (apiResponseData.getStatus() == "200") {
                return "redirect:/home";
            } else {
                model.addAttribute("loginerror", apiResponseData.getMessage());
                return "login";
            }

        } catch (Exception e) {
            return "login";
        }

    }

    @SuppressWarnings("unchecked")
    @PostMapping("/registerapp")
    public String hasilpendaftaran(@Valid @ModelAttribute("registerForm") registerForm registerForm,
            BindingResult bindingResult, HttpServletResponse response, org.springframework.ui.Model model) {
        if (bindingResult.hasErrors()) {
            List<String> errorsall = bindingResult.getAllErrors().stream().map(
                    errors -> errors.getDefaultMessage()).collect(Collectors.toList());
            model.addAttribute("loginerror", errorsall);
            return "register";
        }

        try {
            CreateUserDTO createUserDTO = new CreateUserDTO(registerForm.name(), registerForm.email(),
                    registerForm.password());
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            ResponseEntity<?> entityres = usersController.Cretateuser(headers, createUserDTO);
            apiResponseData = (ApiResponseData<Object>) entityres.getBody();
            // System.out.println(apiResponseData.getStatus());
            if (apiResponseData.getStatus() == "200") {
                model.addAttribute("loginsukses", apiResponseData.getMessage());
                return "redirect:/loginapp";
            } else {
                model.addAttribute("loginerror", apiResponseData.getMessage());
                return "redirect:/loginapp";
            }
        } catch (Exception e) {
            return "register";
        }

    }

    // for update status user
    @PostMapping("/usersform/updatestatusactive")
    public String updatestatusactive(@RequestParam String iduser, @RequestParam String name,
            @RequestParam boolean activestatus, @RequestParam String flagstatus, Model model) {
        if (flagstatus.matches("1")) {
            List<Users> allusers = serviceUser.delete_userpersonal(iduser);
            model.addAttribute("users", allusers);
        } else {
            List<Users> allusers = serviceUser.update_userpersonal(iduser, name, activestatus);
            model.addAttribute("users", allusers);
        }
        return "redirect:/usersform";
    }

}
