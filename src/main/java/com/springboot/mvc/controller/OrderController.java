package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import com.springboot.mvc.dto.OrderRequestDto;
import com.springboot.mvc.service.ICustomerService;
import com.springboot.mvc.service.IItemService;
import com.springboot.mvc.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.springboot.mvc.service.IOrderService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final String
            ORDER_LIST = "orders",
            ORDER_BY_ID = "order",
            FORM = "create_order",
            RESULT = "order_result",
            ERROR = "error";

    @Autowired private IOrderService orderService;
    @Autowired private ICustomerService customerService;
    @Autowired private IItemService itemService;

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<OrderDto> orders = orderService.selectAll();
        List<CustomerDto> customers = customerService.selectAll();
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        return ORDER_LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value="id") Integer id) {
        OrderDto order = orderService.findById(id);
        if (order == null) {
            String message = "Requested order could not be found!";
            model.addAttribute("error", message);
            return ERROR;
        } else {
            CustomerDto customer = customerService.findById(order.getCustomerId());
            List<ItemDto> items = itemService.selectAllByOrder(order);
            Map<String, String> symbols = ValidationUtil.getItemsSymbols(items);
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
        Map<String, String> symbols = ValidationUtil.getItemsSymbols(items);
        model.addAttribute("customers", customers);
        model.addAttribute("items", items);
        model.addAttribute("symbols", symbols);
        model.addAttribute("order", new OrderRequestDto(items));
        return FORM;
    }

    @PostMapping("/add")
    public String addOrder(Model model, @ModelAttribute(name = "order") OrderRequestDto order) {
        Integer customerId = order.getCustomerId();
        List<ItemDto> items = new ArrayList<>();
        for (Integer itemId : order.getItemsIds().keySet())
            if (order.getItemsIds().get(itemId).equals("true"))
                items.add(itemService.findById(itemId));
        OrderDto newOrder = orderService.addOrder(customerId, items);
        CustomerDto customer = customerService.findById(customerId);
        Map<String, String> symbols = ValidationUtil.getItemsSymbols(items);
        model.addAttribute("newOrder", newOrder);
        model.addAttribute("customer", customer);
        model.addAttribute("items", items);
        model.addAttribute("symbols", symbols);
        return RESULT;
    }
}
