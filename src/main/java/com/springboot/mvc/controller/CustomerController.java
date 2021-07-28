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

    private static final String
            CUSTOMER_LIST = "customer/list",
            CUSTOMER_BY_ID = "customer/id",
            FORM = "customer/form",
            RESULT = "customer/result",
            ERROR = "error";

    @Autowired
    private ICustomerService customerService;

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<CustomerDto> customers = customerService.selectAll();
        model.addAttribute("customers", customers);
        return CUSTOMER_LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value = "id") Integer id) {
        CustomerDto customer = customerService.findById(id);
        if (customer == null) {
            String message = "Requested customer could not be found!";
            model.addAttribute("error", message);
            return ERROR;
        } else {
            model.addAttribute("customer", customer);
            return CUSTOMER_BY_ID;
        }
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
