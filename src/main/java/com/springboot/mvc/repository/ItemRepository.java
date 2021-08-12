package com.springboot.mvc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.mvc.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    Optional<ItemEntity> findById(Integer id);
    ItemEntity save(ItemEntity entity);
}