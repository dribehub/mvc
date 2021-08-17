package com.springboot.mvc.controller;

import com.springboot.mvc.dto.*;
import com.springboot.mvc.service.UserService;
import com.springboot.mvc.service.ItemService;
import com.springboot.mvc.util.OrderStatus;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.springboot.mvc.service.OrderService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final String
            LIST = "order/list",
            ACTIVE = "order/active",
            ORDER = "order/id",
            FORM = "order/form",
            RESULT = "order/result",
            ERROR = "error";

    private final AuthController auth;
    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public OrderController(AuthController auth, OrderService orderService,
                           UserService userService, ItemService itemService) {
        this.auth = auth;
        this.orderService = orderService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        addLoggedInUser(model);
        List<OrderDto> orders = orderService.selectAll();
        List<UserDto> customers = userService.selectAll();
        String[] statuses = OrderStatus.getAllStatuses();
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        model.addAttribute("statuses", statuses);
        return LIST;
    }

    @GetMapping("/active")
    public String getActive(Model model) {
        addLoggedInUser(model);
        UserDto user = auth.getLoggedInUser();
        List<OrderDto> orders = orderService.selectAllFromUser(user.getId());
        List<UserDto> customers = userService.selectAll();
        String[] statuses = OrderStatus.getAllStatuses();
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        model.addAttribute("statuses", statuses);
        return ACTIVE;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value = "id") Integer id) {
        addLoggedInUser(model);
        OrderDto order = orderService.findById(id);
        if (order == null) {
            model.addAttribute("error", Utils.ORDER_NOT_FOUND);
            return ERROR;
        } else {
            UserDto customer = userService.findById(order.getCustomerId());
            List<ItemDto> items = itemService.selectAllByOrder(order);
            Map<String, String> symbols = Utils.getAllSymbols(items);
            model.addAttribute("order", order);
            model.addAttribute("customer", customer);
            model.addAttribute("symbols", symbols);
            return ORDER;
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        addLoggedInUser(model);
        List<ItemDto> items = itemService.selectAll();
        model.addAttribute("items", items);
        model.addAttribute("symbols", Utils.getAllSymbols(items));
        model.addAttribute("customers", userService.selectAll());
        model.addAttribute("order", new OrderRequestDto(items));
        return FORM;
    }

    @PostMapping("/add")
    public String addOrder(Model model, @ModelAttribute(name = "order") OrderRequestDto order) {
        addLoggedInUser(model);
        List<ItemDto> items = new ArrayList<>();
        Set<Integer> itemIds = order.getItemIds().keySet();
        for (Integer itemId : itemIds)
            if (order.getItemIds().get(itemId))
                items.add(itemService.findById(itemId));
        Map<String, String> symbols = Utils.getAllSymbols(items);
        Integer customerId = order.getCustomerId();
        OrderDto newOrder = orderService.add(customerId, items);
        UserDto customer = userService.findById(customerId);
        model.addAttribute("items", items);
        model.addAttribute("symbols", symbols);
        model.addAttribute("newOrder", newOrder);
        model.addAttribute("customer", customer);
        return RESULT;
    }

    @RequestMapping(value = "/{id}/delete")
    public String deleteById(@PathVariable(value = "id") Integer id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/{id}/approve")
    public String approveById(@PathVariable(value = "id") Integer id) {
        OrderDto order = orderService.findById(id);
        if (order != null)
            orderService.approve(order); // FIXME: don't reinsert, update instead
        return "redirect:/orders";
    }

    private void addLoggedInUser(Model model) {
        model.addAttribute("user", auth.getLoggedInUser());
    }
}
