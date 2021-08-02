package com.springboot.mvc.dto;

import com.springboot.mvc.entity.CategoryEntity;
import com.springboot.mvc.util.Utils;

import javax.validation.constraints.Pattern;
import java.util.List;

public class CategoriesRequestDto {

    List<CategoryEntity> categories;
    @Pattern(regexp = Utils.NAME_REGEX, message = "Category is invalid!")
    private String newCategory;

    public CategoriesRequestDto() {}
    public CategoriesRequestDto(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<CategoryEntity> getCategories() { return categories; }
    public void setCategories(List<CategoryEntity> categories) { this.categories = categories; }
    public String getNewCategory() { return newCategory; }
    public void setNewCategory(String newCategory) { this.newCategory = newCategory; }
}
