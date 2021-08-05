package com.springboot.mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRequestDto {

    private List<CategoryDto> categories;
    private List<Boolean> areDeleted;

    public CategoriesRequestDto() {}
    public CategoriesRequestDto(List<CategoryDto> categories) {
        this.categories = categories;
        areDeleted = new ArrayList<>();
        categories.forEach(ctg -> areDeleted.add(false));
    }

    public List<CategoryDto> getCategories() { return categories; }
    public List<Boolean> getAreDeleted() { return areDeleted; }
    public CategoryDto getCategory(Integer index) { return categories.get(index); }
    public Boolean isDeleted(Integer index) { return areDeleted.get(index); }

    public void setCategories(List<CategoryDto> categories) { this.categories = categories; }
    public void setAreDeleted(List<Boolean> areDeleted) { this.areDeleted = areDeleted; }
    public void setCategory(Integer index, CategoryDto category) { categories.set(index, category); }
    public void setDeleted(Integer index, Boolean isDeleted) { areDeleted.set(index, isDeleted); }
}
