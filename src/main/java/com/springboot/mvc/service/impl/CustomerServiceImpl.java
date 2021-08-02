package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.mapper.CustomerMapper;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public CustomerDto findById(Integer id) {
        return repository.findById(id)
                .map(CustomerMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<CustomerDto> selectAll() {
        return repository.findAll()
                .stream().map(CustomerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto addCustomer(CustomerDto newCustomer) {
        List<String> emails = selectAll()
                .stream().map(CustomerDto::getEmail)
                .collect(Collectors.toList());
        if (emails.contains(newCustomer.getEmail()))
            throw new NonUniqueResultException("This email is already inserted!");
        return CustomerMapper.toDto(repository.save(CustomerMapper.toEntity(newCustomer)));
    }
}