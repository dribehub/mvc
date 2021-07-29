package com.springboot.mvc.service;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    List<OrderDto> selectAll();
    OrderDto findById(Integer id);
    OrderDto addOrder(Integer customerId, List<ItemDto> items);
    OrderDto addOrder(OrderDto order);
}
