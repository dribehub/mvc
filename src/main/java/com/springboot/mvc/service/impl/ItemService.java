package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.mapper.ItemMapper;
import com.springboot.mvc.service.IItemService;
import com.springboot.mvc.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService implements IItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public ItemDto findById(Integer id) {
        return repository.findById(id)
                .map(ItemMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ItemDto> selectAll() {
        return repository.findAll()
                .stream().map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> selectAllByOrder(OrderDto order) {
        return order.getItems();
    }

    @Override
    public ItemDto addItem(ItemDto item) {
        return ItemMapper.toDto(repository.save(ItemMapper.toEntity(item)));
    }
}