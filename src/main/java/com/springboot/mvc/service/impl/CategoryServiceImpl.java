package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.entity.CategoryEntity;
import com.springboot.mvc.mapper.CategoryMapper;
import com.springboot.mvc.repository.CategoryRepository;
import com.springboot.mvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<CategoryEntity> selectAll() {
        return repository.findAll();
    }

    @Override
    public CategoryEntity findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public CategoryEntity addCategory(CategoryDto newCategory) {
        return repository.save(CategoryMapper.toEntity(newCategory));
    }
}
