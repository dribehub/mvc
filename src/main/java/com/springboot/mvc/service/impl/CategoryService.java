package com.springboot.mvc.service.impl;

import com.springboot.mvc.entity.CategoryEntity;
import com.springboot.mvc.repository.CategoryRepository;
import com.springboot.mvc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

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
    public CategoryEntity addCategory(CategoryEntity newCategory) {
        return repository.save(newCategory);
    }
}
