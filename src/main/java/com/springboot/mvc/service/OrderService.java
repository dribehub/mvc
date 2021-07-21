package com.springboot.mvc.service;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.entity.ItemEntity;

import java.util.List;

public interface OrderService {

    OrderDto findById(Integer id);
    List<OrderDto> selectAll();
    OrderDto addOrder(Integer customerId, List<ItemEntity> items);
}
