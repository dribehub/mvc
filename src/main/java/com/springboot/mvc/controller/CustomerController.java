package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
            EDIT = "customer/edit",
            ERROR = "error";

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<CustomerDto> customers = customerService.selectAll();
        model.addAttribute("customers", customers);
        return CUSTOMER_LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value = "id") Integer id) {
        CustomerDto customer = customerService.findById(id);
        if (customer == null) { // if customer doesn't exist, throw error
            String message = "Requested customer could not be found!";
            model.addAttribute("error", message);
            return ERROR;
        } else { // else show corresponding customer
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
    public String addCustomer(@ModelAttribute(name = "customer") @Valid CustomerDto customer,
                              BindingResult result, Model model) {
        if (result.hasErrors()) return FORM;

        if (customerService.existsByEmail(customer)) { // if customer email exists
            // do not add customer, throw error instead
            model.addAttribute("nonUniqueEmailError", Utils.EMAIL_NOT_UNIQUE);
            model.addAttribute("customer", customer);
            return FORM;
        }

        // else, add customer
        CustomerDto newCustomer = customerService.addCustomer(customer);
        model.addAttribute("customer", newCustomer);
        return RESULT;
    }

    @PutMapping("/{id}/edit")
    public String editById(Model model, @PathVariable(value = "id") Integer id) {
        CustomerDto customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return EDIT;
    }
}
