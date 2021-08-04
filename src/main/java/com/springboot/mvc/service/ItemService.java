package com.springboot.mvc.service;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface ItemService {

    Boolean contains(ItemDto item);
    Boolean contains(CategoryDto categoryDto);
    List<ItemDto> selectAll();
    List<ItemDto> selectAllByOrder(OrderDto order);
    ItemDto findById(Integer id);
    Boolean isPresent(ItemDto item);

    ItemDto add(ItemDto item) throws NonUniqueResultException;

    ItemDto delete(ItemDto item);

    CategoryDto updateCategory(CategoryDto current, CategoryDto updated);
}