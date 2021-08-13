package com.springboot.mvc.mapper;

import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.entity.OrderEntity;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderEntity toEntity(OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setCustomerId(dto.getCustomerId());
        entity.setStatus(dto.getStatus());
        entity.setItems(dto.getItems()
                .stream().map(ItemMapper::toEntity)
                .collect(Collectors.toList()));
        return entity;
    }

    public static OrderDto toDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setCustomerId(entity.getCustomerId());
        dto.setStatus(entity.getStatus());
        dto.setItems(entity.getItems()
                .stream().map(ItemMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
