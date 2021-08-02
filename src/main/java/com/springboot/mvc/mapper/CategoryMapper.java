package com.springboot.mvc.mapper;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.entity.CategoryEntity;

public class CategoryMapper {

    public static CategoryEntity toEntity(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        return entity;
    }

    public static CategoryDto toDto(CategoryEntity entity) {
        CategoryDto dto = new CategoryDto();
        dto.setName(entity.getName());
        return dto;
    }
}
