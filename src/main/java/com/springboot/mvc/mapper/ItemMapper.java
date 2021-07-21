package com.springboot.mvc.mapper;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.entity.ItemEntity;

public class ItemMapper {

    public static ItemEntity toEntity(ItemDto dto) {
        ItemEntity entity = new ItemEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setPrice(dto.getPrice());
        entity.setCurrency(dto.getCurrency());
        entity.setOrders(dto.getOrders());
        return entity;
    }

    public static ItemDto toDto(ItemEntity entity) {
        ItemDto dto = new ItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());
        dto.setCurrency(entity.getCurrency());
        dto.setOrders(entity.getOrders());
        return dto;
    }
}