package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.dto.OrderRequestDto;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.service.ItemService;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.springboot.mvc.service.OrderService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final String
            ORDER_LIST = "order/list",
            ORDER_BY_ID = "order/id",
            FORM = "order/form",
            RESULT = "order/result",
            ERROR = "error";

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ItemService itemService;

    @Autowired
    public OrderController(OrderService orderService,
                           CustomerService customerService,
                           ItemService itemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.itemService = itemService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<OrderDto> orders = orderService.selectAll();
        List<CustomerDto> customers = customerService.selectAll();
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        return ORDER_LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value = "id") Integer id) {
        OrderDto order = orderService.findById(id);
        if (order == null) {
            String message = "Requested order could not be found!";
            model.addAttribute("error", message);
            return ERROR;
        } else {
            CustomerDto customer = customerService.findById(order.getCustomerId());
            List<ItemDto> items = itemService.selectAllByOrder(order);
            Map<String, String> symbols = Utils.getAllSymbols(items);
            model.addAttribute("order", order);
            model.addAttribute("customer", customer);
            model.addAttribute("symbols", symbols);
            return ORDER_BY_ID;
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        List<CustomerDto> customers = customerService.selectAll();
        List<ItemDto> items = itemService.selectAll();
        Map<String, String> symbols = Utils.getAllSymbols(items);
        model.addAttribute("order", new OrderRequestDto(items));
        model.addAttribute("customers", customers);
        model.addAttribute("items", items);
        model.addAttribute("symbols", symbols);
        return FORM;
    }

    @PostMapping("/add")
    public String addOrder(Model model, @ModelAttribute(name = "order") OrderRequestDto order) {
        List<ItemDto> items = new ArrayList<>();
        Set<Integer> itemIds = order.getItemIds().keySet();
        for (Integer itemId : itemIds)
            if (order.getItemIds().get(itemId))
                items.add(itemService.findById(itemId));
        Map<String, String> symbols = Utils.getAllSymbols(items);
        Integer customerId = order.getCustomerId();
        OrderDto newOrder = orderService.addOrder(customerId, items);
        CustomerDto customer = customerService.findById(customerId);
        model.addAttribute("items", items);
        model.addAttribute("symbols", symbols);
        model.addAttribute("newOrder", newOrder);
        model.addAttribute("customer", customer);
        return RESULT;
    }
}
