package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final String LIST = "customer/list";
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
        return LIST;
    }

    @RequestMapping(value = "/{id}/delete")
    public String deleteById(@PathVariable(value = "id") Integer id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("/rdr")
    public String redirect(Model model) {
        loggedInUser = (CustomerDto) model.getAttribute("user");
        return "redirect:/customers";
    }

    private void addLoggedInUser(Model model) {
        model.addAttribute("user", loggedInUser);
    }
}
