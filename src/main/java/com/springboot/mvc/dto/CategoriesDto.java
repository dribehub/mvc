package com.springboot.mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDto {

    private List<CategoryDto> categories;
    private List<Boolean> deleted;

    public CategoriesDto() {}
    public CategoriesDto(List<CategoryDto> categories) {
        this.categories = categories;
        this.deleted = new ArrayList<>();
        categories.forEach(ctg -> deleted.add(false));
    }

    public List<CategoryDto> getCategories() { return categories; }
    public List<Boolean> getDeleted() { return deleted; }
    public CategoryDto getCategory(Integer index) { return categories.get(index); }
    public Boolean isDeleted(Integer index) { return deleted.get(index); }

    public void setCategories(List<CategoryDto> categories) { this.categories = categories; }
    public void setCategory(Integer index, CategoryDto category) { categories.set(index, category); }
    public void setDeleted(List<Boolean> deleted) { this.deleted = deleted; }
    public void setDeleted(Integer index, Boolean isDeleted) { deleted.set(index, isDeleted); }
}
