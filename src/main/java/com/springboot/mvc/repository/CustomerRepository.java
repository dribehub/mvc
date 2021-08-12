package com.springboot.mvc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.mvc.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    Boolean existsByEmail(String email);
    Optional<CustomerEntity> findById(Integer id);
    Optional<CustomerEntity> findByEmail(String email);
    CustomerEntity save(CustomerEntity entity);
}