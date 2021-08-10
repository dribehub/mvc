package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
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
    private CustomerDto loggedInUser;

    @Autowired
    public AuthController(CustomerService customerService) {
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

    @GetMapping("/logout")
    public String logout(Model model) {
        loggedInUser = null;
        return LOGIN;
    }

    @PostMapping("/perform_signup")
    public String performSignup(@ModelAttribute(name = "user") @Valid CustomerDto customer,
                                BindingResult result, Model model) {
        if (customerService.existsByEmail(customer)) {
            model.addAttribute("nonUniqueEmailError", Utils.EMAIL_NOT_UNIQUE);
            return SIGNUP;
        } else if (result.hasErrors()) {
            return SIGNUP;
        } else {
            customer.setRole("user");
            loggedInUser = customerService.addCustomer(customer);
            return "redirect:/home";
        }
    }

    @PostMapping("/perform_login")
    public String performLogin(@ModelAttribute(name = "user") CustomerDto customer, Model model) {
        if (!customerService.existsByEmail(customer)) {
            model.addAttribute("user", customer);
            model.addAttribute("invalidEmailError", Utils.EMAIL_NOT_FOUND);
            return LOGIN;
        } else if (!customerService.isValid(customer)) {
            model.addAttribute("user", customer);
            model.addAttribute("invalidPassError", Utils.INVALID_PASS);
            return LOGIN;
        } else {
            loggedInUser = customer;
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (loggedInUser == null) return LOGIN;
        model.addAttribute("user", loggedInUser);
        return INDEX;
    }
}
