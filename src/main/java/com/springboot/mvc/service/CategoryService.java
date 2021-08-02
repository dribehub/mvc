package com.springboot.mvc.service;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> selectAll();
    CategoryEntity findByName(String name);
    CategoryEntity addCategory(CategoryDto newCategory);
}
