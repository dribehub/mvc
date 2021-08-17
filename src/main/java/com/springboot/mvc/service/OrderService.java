package com.springboot.mvc.service;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> selectAll();
    OrderDto findById(Integer id);

    OrderDto add(Integer customerId, List<ItemDto> items);
    OrderDto add(OrderDto order);

    OrderDto update(OrderDto current, OrderDto updated);
    boolean approve(OrderDto order);

    OrderDto deleteById(Integer id);
}
