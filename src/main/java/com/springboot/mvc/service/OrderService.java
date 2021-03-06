package com.springboot.mvc.service;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.dto.UserDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> selectAll();
    List<OrderDto> selectAllFromUser(Integer customerId);
    OrderDto findById(Integer id);
    OrderDto add(Integer customerId, List<ItemDto> items);
    OrderDto add(OrderDto order);
    OrderDto update(OrderDto current, OrderDto updated);
    Boolean approveById(Integer id);
    OrderDto deleteById(Integer id);
}
