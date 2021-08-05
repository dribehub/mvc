package com.springboot.mvc.service;

import com.springboot.mvc.dto.CategoriesRequestDto;
import com.springboot.mvc.dto.CategoryDto;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> selectAll();
    CategoriesRequestDto selectAllToDto();
    CategoryDto findByName(String name);
    Boolean exists(CategoryDto category);

    CategoryDto add(CategoryDto newCategory)
            throws NonUniqueResultException;

    CategoryDto delete(CategoryDto category);
    CategoryDto deleteByName(String name);

    CategoryDto update(CategoryDto current, CategoryDto updated)
            throws NonUniqueResultException;
}
