package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private static final String
            SIGNUP = "signup",
            LOGIN = "login",
            INDEX = "index";

    private final CustomerService customerService;

    @Autowired
    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new CustomerDto());
        return SIGNUP;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new CustomerDto());
        return LOGIN;
    }

    @PostMapping("/perform_login")
    public String performLogin(@ModelAttribute CustomerDto customer, Model model) {
        if (!customerService.existsByEmail(customer)) {
            model.addAttribute("invalidEmailError", Utils.EMAIL_NOT_FOUND);
            return LOGIN;
        } else if (!customerService.isValid(customer)) {
            model.addAttribute("invalidPassError", Utils.INVALID_PASS);
            return LOGIN;
        } else return INDEX;
    }

    @GetMapping("/login?error=true")
    public String throwError() {
        return LOGIN;
    }
}
