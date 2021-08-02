package com.springboot.mvc.service;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> selectAll();
    List<ItemDto> selectAllByOrder(OrderDto order);
    ItemDto findById(Integer id);
    ItemDto addItem(ItemDto item);
}