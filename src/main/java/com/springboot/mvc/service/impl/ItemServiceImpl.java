package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.mapper.ItemMapper;
import com.springboot.mvc.service.ItemService;
import com.springboot.mvc.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Checks if the given item is present
     * @param item the ItemDto to be checked
     * @return true if item is present
     */
    @Override
    public Boolean contains(ItemDto item) {
        return selectAll().contains(item);
    }

    /**
     * Checks if the given category holds any items
     * @param category the category to be checked
     * @return true if category contains at least one item
     */
    @Override
    public Boolean contains(CategoryDto category) {
        return selectAll().stream().anyMatch(item ->
                category.getName().equals(item.getCategory()));
    }

    /**
     * Finds the amount of items in the category
     * @param category the category to be filtered
     * @return the number of items in the given category
     */
    @Override
    public Long getNumOfItems(CategoryDto category) {
        return selectAll().stream()
                .map(ItemDto::getCategory)
                .filter(category::equals)
                .count();
    }

    /**
     * Finds any item by the given item id
     * @param id the item id (unique)
     * @return the item if one is found, otherwise null
     */
    @Override
    public ItemDto findById(Integer id) {
        return repository.findById(id)
                .map(ItemMapper::toDto)
                .orElse(null);
    }

    /**
     * Selects all items in the repository
     * @return a list of all items found in the repository
     */
    @Override
    public List<ItemDto> selectAll() {
        return repository.findAll()
                .stream().map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Adds the given item to the repository if it's not already present
     * @param item the item to be inserted
     * @return the inserted item if it's not already present
     * @throws NonUniqueResultException if item is already present
     */
    @Override
    public ItemDto add(ItemDto item) throws NonUniqueResultException {
        if (isUnique(item))
            throw new NonUniqueResultException(item.getName());
        return ItemMapper.toDto(repository.save(ItemMapper.toEntity(item)));
    }

    /**
     * Overwrites the given item if it's present in the repository,
     * otherwise simply adds it to the repository
     * @param item the item to be overwritten
     * @return the item that was saved
     */
    @Override
    public ItemDto overwrite(ItemDto item) {
        return ItemMapper.toDto(repository.save(ItemMapper.toEntity(item)));
    }

    /**
     * Checks if the given item's name and category are unique within the item repository
     * @param newItem the item to be checked for uniqueness
     * @return true if no item in the repository is found to have newItem's name and category
     */
    @Override
    public Boolean isUnique(ItemDto newItem) {
        return selectAll().stream().noneMatch(item -> item.equalsLogically(newItem));
    }

    /**
     * Removes the given item from the repository
     * @param item the item to be deleted
     * @return the deleted item if one is found, otherwise null
     */
    @Override
    public ItemDto delete(ItemDto item) {
        if (contains(item)) {
            repository.delete(ItemMapper.toEntity(item));
            return item;
        }
        return null;
    }

    /**
     * Updates the 'current' item's category with the new 'updated' category
     * @param current the category already present in the repository
     * @param updated the updated category
     * @return the updated category if items of that category are found, otherwise null
     * @throws NonUniqueResultException if current == updated
     */
    @Override
    public CategoryDto updateCategory(CategoryDto current, CategoryDto updated) {
        if (contains(current)) {
            for (ItemDto item : selectAll()) {
                if (current.equals(item.getCategory())) {
                    item.setCategory(updated);
                    overwrite(item);
                }
            }
            return updated;
        }
        return null;
    }
}