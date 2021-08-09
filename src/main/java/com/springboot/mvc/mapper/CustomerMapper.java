package com.springboot.mvc.mapper;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity toEntity(CustomerDto dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(dto.getId());
        entity.setRole(dto.getRole());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static CustomerDto toDto(CustomerEntity entity) {
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}