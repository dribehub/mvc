package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    private static final String CUSTOMER_LIST = "customers";
    private static final String CUSTOMER_BY_ID = "customer";
    private static final String FORM = "create_customer";
    private static final String RESULT = "customer_result";

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<CustomerDto> customers = customerService.selectAll();
        model.addAttribute("customers", customers);
        return CUSTOMER_LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value="id") Integer id) {
        CustomerDto customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return CUSTOMER_BY_ID;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return FORM;
    }

    @PostMapping("/add")
    public String addCustomer(@Valid @ModelAttribute(name = "customer") CustomerDto customer,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer", customer);
            return FORM;
        }

        try {
            CustomerDto newCustomer = customerService.addCustomer(customer);
            model.addAttribute("customer", newCustomer);
            return RESULT;
        } catch (NonUniqueResultException ex) {
            model.addAttribute("nonUniqueEmailError", ex.getMessage());
            model.addAttribute("customer", customer);
            return FORM;
        }
    }
}
