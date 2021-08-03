package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.CategoriesRequestDto;
import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.mapper.CategoryMapper;
import com.springboot.mvc.repository.CategoryRepository;
import com.springboot.mvc.service.CategoryService;
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
    public CategoryDto addCategory(CategoryDto newCategory) {
        String ctgName = newCategory.getName();
        List<String> ctgNames = selectAll()
                .stream().map(CategoryDto::getName)
                .collect(Collectors.toList());

        if (ctgNames.contains(ctgName)) {
            String message = String.format("Category \"%s\" is already inserted!", ctgName);
            throw new NonUniqueResultException(message);
        }

        return CategoryMapper.toDto(repository
                .save(CategoryMapper.toEntity(newCategory)));
    }
}
