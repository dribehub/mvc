package com.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static final String
            SIGNUP = "signup",
            LOGIN = "login";

    @GetMapping("/signup")
    public String signup() {
        return SIGNUP;
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN;
    }
}
