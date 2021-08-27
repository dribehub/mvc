package com.springboot.mvc.service;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.dto.ItemDto;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface ItemService {

    Boolean contains(ItemDto item);
    Boolean contains(CategoryDto category);
    Long getNumOfItems(CategoryDto category);
    List<ItemDto> selectAll();
    List<ItemDto> selectAllByCategory(String category);
    ItemDto findById(Integer id);
    Boolean isUnique(ItemDto item);
    ItemDto add(ItemDto item) throws NonUniqueResultException;
    ItemDto overwrite(ItemDto item);
    ItemDto delete(ItemDto item);
    ItemDto deleteById(Integer id);
    ItemDto update(ItemDto updated);
    CategoryDto updateCategory(CategoryDto current, CategoryDto updated);
}