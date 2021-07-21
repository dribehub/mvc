package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.dto.OrderRequestDto;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.springboot.mvc.service.OrderService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private CustomerService customerService;
    @Autowired private ItemService itemService;

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value="id") Integer id) {
        OrderDto order = orderService.findById(id);
        CustomerDto customer = customerService.findById(order.getCustomerId());
        model.addAttribute("order", order);
        model.addAttribute("customer", customer);
        return "order";
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        model.addAttribute("orders", orderService.selectAll());
        model.addAttribute("customers", customerService.selectAll());
        return "orders";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("customers", customerService.selectAll());
        model.addAttribute("items", itemService.selectAll());
        return "create_order";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute(name = "order") OrderRequestDto order) {
        orderService.addOrder(order.getCustomerId(), order.getItems());
        return "create_order";
    }
}
