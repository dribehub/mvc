package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.entity.OrderEntity;
import com.springboot.mvc.mapper.ItemMapper;
import com.springboot.mvc.mapper.OrderMapper;
import com.springboot.mvc.service.IOrderService;
import com.springboot.mvc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public List<OrderDto> selectAll() {
        return repository.findAll()
                .stream().map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Integer id) {
        return repository.findById(id)
                .map(OrderMapper::toDto)
                .orElse(null);
    }

    @Override
    public OrderDto addOrder(Integer customerId, List<ItemDto> items) {
        OrderEntity entity = new OrderEntity();
        entity.setDate(LocalDate.now());
        entity.setCustomerId(customerId);
        entity.setItems(items.stream()
                .map(ItemMapper::toEntity)
                .collect(Collectors.toList()));
        return OrderMapper.toDto(repository.save(entity));
    }
}