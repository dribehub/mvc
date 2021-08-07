package com.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static final String LOGIN = "login";

    @GetMapping("/login")
    public String login() {
        return LOGIN;
    }
}
