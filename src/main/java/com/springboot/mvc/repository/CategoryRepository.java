package com.springboot.mvc.repository;

import com.springboot.mvc.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    Boolean existsByName(String name);
    List<CategoryEntity> findAll();
    Optional<CategoryEntity> findByName(String name);
    CategoryEntity save(CategoryEntity entity);
}
