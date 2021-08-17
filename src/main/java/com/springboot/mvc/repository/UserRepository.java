package com.springboot.mvc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.mvc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByEmail(String email);
    Optional<UserEntity> findById(Integer id);
    Optional<UserEntity> findByEmail(String email);
    UserEntity save(UserEntity entity);
}