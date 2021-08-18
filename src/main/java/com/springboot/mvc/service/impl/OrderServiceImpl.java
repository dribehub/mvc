package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.entity.OrderEntity;
import com.springboot.mvc.mapper.ItemMapper;
import com.springboot.mvc.mapper.OrderMapper;
import com.springboot.mvc.service.OrderService;
import com.springboot.mvc.repository.OrderRepository;
import com.springboot.mvc.util.OrderStatus;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override public List<OrderDto> selectAll() {
        return repository.findAll()
                .stream().map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override public List<OrderDto> selectAllFromUser(Integer id) {
        return repository.findAll()
                .stream().map(OrderMapper::toDto)
                .filter(order -> order.getCustomerId().equals(id))
                .collect(Collectors.toList());
    }
    @Override public OrderDto findById(Integer id) {
        return repository.findById(id)
                .map(OrderMapper::toDto)
                .orElse(null);
    }
    @Override public OrderDto add(Integer customerId, List<ItemDto> items) {
        OrderEntity entity = new OrderEntity();
        entity.setDate(LocalDate.now());
        entity.setCustomerId(customerId);
        entity.setStatus(OrderStatus.PENDING.code());
        entity.setItems(items.stream()
                .map(ItemMapper::toEntity)
                .collect(Collectors.toList()));
        return OrderMapper.toDto(repository.save(entity));
    }
    @Override public OrderDto add(OrderDto order) {
        return add(order.getCustomerId(), order.getItems());
    }
    @Override public OrderDto update(OrderDto current, OrderDto updated) {
        repository.save(OrderMapper.toEntity(updated));
        return current;
    }
    @Override public Boolean approveById(Integer id) {
        OrderDto order = findById(id);
        if (order != null && order.getStatus().equals(OrderStatus.PENDING.code())) {
            OrderDto updated = new OrderDto(order);
            updated.setStatus(OrderStatus.APPROVED.code());
            update(order, updated);
            return true;
        } else {
            return false;
        }
    }
    @Override public OrderDto deleteById(Integer id) {
        OrderEntity order = repository.findById(id).orElse(null);
        if (order != null) {
            OrderDto dto = OrderMapper.toDto(order);
            repository.delete(order);
            return dto;
        } else {
            return null;
        }
    }
}
