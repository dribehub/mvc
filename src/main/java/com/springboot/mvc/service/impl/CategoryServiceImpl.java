package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.CategoriesRequestDto;
import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.mapper.CategoryMapper;
import com.springboot.mvc.repository.CategoryRepository;
import com.springboot.mvc.service.CategoryService;
import org.hibernate.procedure.ParameterMisuseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<CategoryDto> selectAll() {
        return repository.findAll()
                .stream().map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriesRequestDto selectAllToDto() {
        return new CategoriesRequestDto(selectAll());
    }

    @Override
    public CategoryDto findByName(String name) {
        return CategoryMapper.toDto(repository.findByName(name));
    }

    @Override
    public CategoryDto addCategory(CategoryDto newCategory)
            throws NonUniqueResultException {

        if (isPresent(selectAll(), newCategory)) {
            throw new NonUniqueResultException(newCategory.getName());
        }

        return CategoryMapper.toDto(repository
                .save(CategoryMapper.toEntity(newCategory)));
    }

    @Override
    public CategoryDto deleteCategory(CategoryDto category) {
        repository.delete(CategoryMapper.toEntity(category));
        return category;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto current, CategoryDto updated)
            throws NonUniqueResultException {
        CategoryDto newCtg = addCategory(updated);
        deleteCategory(current);
        return newCtg;
    }

    private static boolean isPresent(List<CategoryDto> categories, CategoryDto category) {
        return categories.stream().anyMatch(ctg -> ctg.equals(category));
    }
}
