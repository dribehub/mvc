package com.springboot.mvc.service;

import com.springboot.mvc.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    Boolean existsByEmail(String email);
    List<CustomerDto> selectAll();
    CustomerDto findById(Integer id);
    CustomerDto addCustomer(CustomerDto newCustomer);
}