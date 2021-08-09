package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private static final String
            SIGNUP = "signup",
            LOGIN = "login",
            INDEX = "index";

    private final CustomerService customerService;

    @Autowired
    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new CustomerDto());
        return SIGNUP;
    }

    @PostMapping("/perform_signup")
    public String performSignup(@ModelAttribute @Valid CustomerDto customer,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return SIGNUP;
        } else if (!customerService.existsByEmail(customer)) {
            model.addAttribute("nonUniqueEmailError", Utils.EMAIL_NOT_UNIQUE);
            return signup(model);
        } else {
            return home(model);
        }
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
            model.addAttribute("user", customer);
            return LOGIN;
        } else if (!customerService.isValid(customer)) {
            model.addAttribute("invalidPassError", Utils.INVALID_PASS);
            model.addAttribute("user", customer);
            return LOGIN;
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        return INDEX;
    }
}
