package com.springboot.mvc.service;

import com.springboot.mvc.dto.MessageDto;
import com.springboot.mvc.dto.UserDto;

import java.util.List;

public interface MessageService {

    List<MessageDto> selectAll();
    MessageDto findById(Integer id);
    MessageDto addMessage(MessageDto message);
    void addMessageToMany(MessageDto message, UserDto[] users);
}