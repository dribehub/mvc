package com.springboot.mvc.service;

import com.springboot.mvc.dto.CategoriesRequestDto;
import com.springboot.mvc.dto.CategoryDto;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> selectAll();
    CategoriesRequestDto selectAllToDto();
    CategoryDto findByName(String name);

    CategoryDto addCategory(CategoryDto newCategory)
            throws NonUniqueResultException;

    CategoryDto deleteCategory(CategoryDto category);

    CategoryDto updateCategory(CategoryDto current, CategoryDto updated)
            throws NonUniqueResultException;
}
