package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.dto.OrderRequestDto;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.springboot.mvc.service.OrderService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<CustomerDto> customers =  customerService.selectAll();
        List<ItemDto> items =  itemService.selectAll();
        model.addAttribute("customers", customers);
        model.addAttribute("items", items);
        model.addAttribute("order", new OrderRequestDto(items));
        return "create_order";
    }

    @PostMapping("/add")
    public String addOrder(Model model, @ModelAttribute(name = "order") OrderRequestDto order) {
        Integer customerId = order.getCustomerId();
        List<ItemDto> items = new ArrayList<>();
        for (ItemDto key : order.getItems().keySet())
            if (order.getItems().get(key))
                items.add(key);
        OrderDto newOrder = orderService.addOrder(customerId, items);
        CustomerDto customer = customerService.findById(customerId);
        model.addAttribute("newOrder", newOrder);
        model.addAttribute("customer", customer);
        return "result";
    }
}
