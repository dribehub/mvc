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
    private CustomerDto loggedInUser;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        addLoggedInUser(model);
        List<CustomerDto> customers = customerService.selectAll();
        model.addAttribute("customers", customers);
        return CUSTOMER_LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value = "id") Integer id) {
        addLoggedInUser(model);
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
        addLoggedInUser(model);
        model.addAttribute("customer", new CustomerDto());
        return FORM;
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute(name = "customer") @Valid CustomerDto customer,
                              BindingResult result, Model model) {
        addLoggedInUser(model);
        if (result.hasErrors()) {
            return FORM;
        } else if (customerService.existsByEmail(customer)) {
            model.addAttribute("nonUniqueEmailError", Utils.EMAIL_NOT_UNIQUE);
            model.addAttribute("customer", customer);
            return FORM;
        } else {
            customer.setRole("user");
            CustomerDto newCustomer = customerService.addCustomer(customer);
            model.addAttribute("customer", newCustomer);
            return RESULT;
        }
    }

    @PutMapping("/{id}/edit")
    public String editById(Model model, @PathVariable(value = "id") Integer id) {
        addLoggedInUser(model);
        CustomerDto customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return EDIT;
    }

    @RequestMapping(value = "/{id}/delete")
    public String deleteById(@PathVariable(value = "id") Integer id) {
        customerService.deleteById(id);
        return "redirect:/customers/";
    }

    @GetMapping("/user/{id}")
    public String redirect(@PathVariable(value = "id") Integer id) {
        loggedInUser = customerService.findById(id);
        return "redirect:/customers";
    }

    private void addLoggedInUser(Model model) {
        model.addAttribute("user", loggedInUser);
    }
}
