package com.springboot.mvc.dto;

import com.springboot.mvc.entity.CategoryEntity;

import java.util.List;

public class CategoriesRequestDto {

    List<CategoryEntity> categories;

    public CategoriesRequestDto() {}
    public CategoriesRequestDto(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
