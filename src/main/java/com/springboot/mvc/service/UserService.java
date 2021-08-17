package com.springboot.mvc.service;

import com.springboot.mvc.dto.UserDto;

import java.util.List;

public interface UserService {

    Boolean existsByEmail(String email);
    Boolean existsByEmail(UserDto user);
    Boolean isValid(UserDto user);
    List<UserDto> selectAll();
    UserDto findById(Integer id);
    UserDto findByEmail(String email);
    UserDto findByEmail(UserDto user);
    UserDto addUser(UserDto newUser);
    UserDto deleteById(Integer id);
}