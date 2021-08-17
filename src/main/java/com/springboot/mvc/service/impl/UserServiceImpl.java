package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.UserDto;
import com.springboot.mvc.entity.UserEntity;
import com.springboot.mvc.mapper.UserMapper;
import com.springboot.mvc.service.UserService;
import com.springboot.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
    @Override public Boolean existsByEmail(UserDto user) {
        return existsByEmail(user.getEmail());
    }
    @Override public Boolean isValid(UserDto user) {
        return user.equals(findByEmail(user));
    }
    @Override public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserMapper::toDto)
                .orElse(null);
    }
    @Override public UserDto findByEmail(String email) {
        return repository.findByEmail(email)
                .map(UserMapper::toDto)
                .orElse(null);
    }
    @Override public UserDto findByEmail(UserDto user) {
        return findByEmail(user.getEmail());
    }
    @Override public List<UserDto> selectAll() {
        return repository.findAll()
                .stream().map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override public UserDto addUser(UserDto newUser) {
        if (existsByEmail(newUser))
            throw new NonUniqueResultException("This email is already inserted!");
        UserEntity entity = UserMapper.toEntity(newUser);
        return UserMapper.toDto(repository.save(entity));
    }
    @Override public UserDto deleteById(Integer id) {
        UserEntity user = repository.findById(id).orElse(null);
        if (user != null) {
            repository.delete(user);
            return UserMapper.toDto(user);
        } else return null;
    }
}