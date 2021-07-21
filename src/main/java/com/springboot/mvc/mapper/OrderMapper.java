package com.springboot.mvc.mapper;

import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.dto.OrderRequestDto;
import com.springboot.mvc.entity.OrderEntity;

import java.time.LocalDate;

public class OrderMapper {

    public static OrderEntity toEntity(OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setCustomerId(dto.getCustomerId());
        entity.setItems(dto.getItems());
        return entity;
    }

    public static OrderDto toDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setCustomerId(entity.getCustomerId());
        dto.setItems(entity.getItems());
        return dto;
    }
}
