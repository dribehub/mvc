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

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<CustomerDto> customers = customerService.selectAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value="id") Integer id) {
        model.addAttribute("customer", customerService.findById(id));
        return "customer";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "create_customer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute(name = "customer")
                              @Valid CustomerDto customer,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer", customer);
            return "create_customer";
        }

        try {
            CustomerDto newCustomer = customerService.addCustomer(customer);
            model.addAttribute("customer", newCustomer);
            return "customer_result";
        } catch (NonUniqueResultException ex) {
            model.addAttribute("nonUniqueEmailError", ex.getMessage());
            model.addAttribute("customer", customer);
            return "create_customer";
        }
    }
}
