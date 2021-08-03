package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.entity.CategoryEntity;
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
    public CategoryDto findByName(String name) {
        return CategoryMapper.toDto(repository.findByName(name));
    }

    @Override
    public CategoryDto addCategory(CategoryDto newCategory) {
        List<String> categories = selectAll()
                .stream().map(CategoryDto::getName)
                .collect(Collectors.toList());
        if (categories.contains(newCategory.getName()))
            throw new NonUniqueResultException("This category is already inserted!");
        return CategoryMapper.toDto(repository.save(CategoryMapper.toEntity(newCategory)));
    }
}
