package com.springboot.mvc.service;

import com.springboot.mvc.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {

    CustomerDto findById(Integer id);
    List<CustomerDto> selectAll();
    CustomerDto addCustomer(CustomerDto newCustomer);
}