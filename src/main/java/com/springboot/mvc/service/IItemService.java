package com.springboot.mvc.service;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;

import java.util.List;

public interface IItemService {

    ItemDto findById(Integer id);
    List<ItemDto> selectAll();
    List<ItemDto> selectAllByOrder(OrderDto order);
    ItemDto addItem(ItemDto item);
}