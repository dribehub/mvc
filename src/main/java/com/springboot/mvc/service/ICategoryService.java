package com.springboot.mvc.service;

import com.springboot.mvc.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {

    List<CategoryEntity> selectAll();
    CategoryEntity findByName(String name);
    CategoryEntity addCategory(CategoryEntity newCategory);
}
