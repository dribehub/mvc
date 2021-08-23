package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.CategoriesDto;
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

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Selects all categories in the repository
     * @return a list of all categories
     */
    @Override
    public List<CategoryDto> selectAll() {
        return repository.findAll()
                .stream().map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Selects all categories in the repository
     * @return a list of all categories wrapped by a DTO
     */
    @Override
    public CategoriesDto selectAllToDto() {
        return new CategoriesDto(selectAll());
    }

    /**
     * Finds any category by the given name
     * @param name the name filter
     * @return the category if one is found, otherwise null
     */
    @Override
    public CategoryDto findByName(String name) {
        return repository.findByName(name)
                .map(CategoryMapper::toDto)
                .orElse(null);
    }

    /**
     * Checks if the given category is present in the repository
     * @param category the category to be checked
     * @return true if the given category is present
     */
    @Override
    public Boolean exists(CategoryDto category) {
        return repository.existsByName(category.getName());
    }

    /**
     * Adds the new category to the repository
     * @param newCategory the new category to be inserted
     * @return the inserted category if it wasn't already present
     * @throws NonUniqueResultException if category is already present
     */
    @Override
    public CategoryDto add(CategoryDto newCategory)
            throws NonUniqueResultException {
        if (exists(newCategory))
            throw new NonUniqueResultException(newCategory.getName());
        return CategoryMapper.toDto(repository
                .save(CategoryMapper.toEntity(newCategory)));
    }

    /**
     * Removes a given category from the repository
     * @param category the category to be deleted
     * @return the deleted category
     */
    @Override
    public CategoryDto delete(CategoryDto category) {
        repository.delete(CategoryMapper.toEntity(category));
        return category;
    }

    /**
     * Updates the current category in the repository
     * @param current the current category to be updated
     * @param updated the updated category
     * @return the updated category on success
     * @throws NonUniqueResultException if category is already present
     */
    @Override
    public CategoryDto update(CategoryDto current, CategoryDto updated)
            throws NonUniqueResultException {
        CategoryDto newCtg = add(updated);
        delete(current);
        return newCtg;
    }

    /**
     * Removes a given category by name from the repository
     * @param name the name of the category to be deleted
     * @return the deleted category
     */
    @Override
    public CategoryDto deleteByName(String name) {
        return delete(findByName(name));
    }
}
