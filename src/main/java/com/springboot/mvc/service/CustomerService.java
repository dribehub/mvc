package com.springboot.mvc.service;

import com.springboot.mvc.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    Boolean existsByEmail(String email);
    Boolean existsByEmail(CustomerDto customer);
    Boolean isValid(CustomerDto customer);
    List<CustomerDto> selectAll();
    CustomerDto findById(Integer id);
    CustomerDto findByEmail(String email);
    CustomerDto findByEmail(CustomerDto customer);
    CustomerDto addCustomer(CustomerDto newCustomer);
    CustomerDto deleteById(Integer id);
}