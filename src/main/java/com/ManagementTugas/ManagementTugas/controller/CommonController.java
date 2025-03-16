package com.ManagementTugas.ManagementTugas.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Accounts;
import com.ManagementTugas.ManagementTugas.PaymentDigital.service.AccountService;
import com.ManagementTugas.ManagementTugas.model.LoginDTO;
import com.ManagementTugas.ManagementTugas.model.Users;
import com.ManagementTugas.ManagementTugas.model.registerForm;
import com.ManagementTugas.ManagementTugas.service.ServiceUser;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CommonController {

    private final UsersController usersController;
    private final ServiceUser serviceUser;

    @Autowired
    private final AccountService accountservice;

    private static ApiResponseData<Object> apiResponseData = new ApiResponseData<>();

    public CommonController(UsersController usersController, ServiceUser serviceUser, AccountService accountservice) {
        this.usersController = usersController;
        this.serviceUser = serviceUser;
        this.accountservice = accountservice;
    }

    @GetMapping("/")
    public String awalLogin(Model model) {
        // model.addAttribute("loginForm", new LoginDTO());
        return "applicationHome";
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

    @SuppressWarnings("unchecked")
    @GetMapping("/home")
    public String tampilanhome(Model model, HttpServletRequest request) {
        apiResponseData = (ApiResponseData<Object>) usersController.validasitokens(request).getBody();
        if (apiResponseData.getStatus().matches("403")) {
            return "redirect:/loginapp";
        }
        return "home";
    }

    @GetMapping("/portfolio")
    public String tampilanprofile(Model model, HttpServletRequest request) {
        apiResponseData = (ApiResponseData<Object>) usersController.validasitokens(request).getBody();
        if (apiResponseData.getStatus().matches("403")) {
            return "redirect:/loginapp";
        }
        return "profile";
    }

    @GetMapping("/retailform")
    public String tampilanretail(Model model) {
        List<Users> users = serviceUser.show_findalldatauser();
        model.addAttribute("users", users);
        return "retailform";
    }

    @GetMapping("/usersform")
    public String tampilandatausers(Model model) {
        List<Users> users = serviceUser.show_findalldatauser();
        users.sort(Comparator.comparing(Users::getActivestatus).reversed());
        model.addAttribute("data_users", users);
        return "usersform";
    }

    @GetMapping(path = "/serviceapp")
    public String serviceApp(Model mode) {
        return "home";
    }

    @GetMapping(path = "/payment")
    public String paymnetDigital(Model model) {
        List<Accounts> accounts = accountservice.getAllAccount();
        model.addAttribute("data_accounts", accounts);
        return "payment/Payment";
    }

}
