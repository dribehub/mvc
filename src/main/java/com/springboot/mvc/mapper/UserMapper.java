package com.springboot.mvc.mapper;

import com.springboot.mvc.dto.UserDto;
import com.springboot.mvc.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setRole(dto.getRole());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}