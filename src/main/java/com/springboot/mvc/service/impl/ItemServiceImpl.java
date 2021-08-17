package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
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

    @Override public Boolean contains(ItemDto item) {
        return selectAll().contains(item);
    }
    @Override public Boolean contains(CategoryDto category) {
        return selectAll().stream().anyMatch(item ->
                category.getName().equals(item.getCategory()));
    }
    @Override public ItemDto findById(Integer id) {
        return repository.findById(id)
                .map(ItemMapper::toDto)
                .orElse(null);
    }
    @Override public List<ItemDto> selectAll() {
        return repository.findAll()
                .stream().map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override public List<ItemDto> selectAllByOrder(OrderDto order) {
        return order.getItems();
    }
    @Override public ItemDto add(ItemDto item) throws NonUniqueResultException {
        if (isPresent(item))
            throw new NonUniqueResultException(item.getName());
        return ItemMapper.toDto(repository.save(ItemMapper.toEntity(item)));
    }
    @Override public Boolean isPresent(ItemDto newItem) {
        return selectAll().stream().anyMatch(item -> item.equalsLogically(newItem));
    }
    @Override public ItemDto delete(ItemDto item) {
        repository.delete(ItemMapper.toEntity(item));
        return item;
    }
    @Override public CategoryDto updateCategory(CategoryDto current, CategoryDto updated) {
        for (ItemDto item : selectAll()) {
            if (current.equals(item.getCategory())) {
                item.setCategory(updated);
                add(item);
            }
        }
        return updated;
    }
}