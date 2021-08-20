package com.springboot.mvc.repository;

import com.springboot.mvc.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    Optional<MessageEntity> findById(Integer id);
    MessageEntity save(MessageEntity entity);
}
