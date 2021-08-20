package com.springboot.mvc.controller;

import com.springboot.mvc.dto.MessageDto;
import com.springboot.mvc.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final AuthController auth;
    private final MessageService messageService;

    public MessageController(AuthController auth, MessageService messageService) {
        this.auth = auth;
        this.messageService = messageService;
    }

    @PostMapping(value = "/new")
    public String messageUser(@ModelAttribute(name = "message") MessageDto messageDto) {
        messageDto.setSenderId(auth.getLoggedInUser().getId());
        messageService.addMessage(messageDto);
        return "redirect:/users";
    }
}
