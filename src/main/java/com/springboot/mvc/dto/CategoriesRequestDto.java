package com.springboot.mvc.dto;

import com.springboot.mvc.entity.CategoryEntity;
import com.springboot.mvc.util.Utils;

import javax.validation.constraints.Pattern;
import java.util.List;

public class CategoriesRequestDto {

    List<CategoryDto> categories;

    public CategoriesRequestDto() {}
    public CategoriesRequestDto(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public List<CategoryDto> getCategories() { return categories; }
    public void setCategories(List<CategoryDto> categories) { this.categories = categories; }
}
