package com.springboot.mvc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.mvc.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findById(Integer id);
    OrderEntity save(OrderEntity entity);
}