package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.entity.OrderEntity;
import com.springboot.mvc.service.ICustomerService;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.springboot.mvc.service.IItemService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/items")
public class ItemController {

    private static final String
            ITEM_LIST = "item/list",
            ITEM_BY_ID = "item/id",
            FORM = "item/form",
            RESULT = "item/result",
            ERROR = "error";

    @Autowired private IItemService itemService;
    @Autowired private ICustomerService customerService;

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<ItemDto> items = itemService.selectAll();
        Map<String, String> symbols = Utils.getAllSymbols(items);
        model.addAttribute("items", items);
        model.addAttribute("symbols", symbols);
        return ITEM_LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value="id") Integer id) {
        ItemDto item = itemService.findById(id);
        if (item == null) {
            String message = "Requested item could not be found!";
            model.addAttribute("error", message);
            return ERROR;
        } else {
            String symbol = Utils.getCurrencySymbol(item.getCurrency());
            model.addAttribute("item", item);
            model.addAttribute("symbol", symbol);
            List<OrderEntity> orders = item.getOrders();
            if (!orders.isEmpty()) {
                model.addAttribute("orders", orders);
                Map<Integer, CustomerDto> customers = new HashMap<>();
                for (OrderEntity order : orders) {
                    Integer customerId = order.getCustomerId();
                    CustomerDto customer = customerService.findById(customerId);
                    customers.put(customerId, customer);
                }
                model.addAttribute("customers", customers);
            }
            return ITEM_BY_ID;
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        List<ItemDto> items = itemService.selectAll();
        model.addAttribute("items", items);
        model.addAttribute("item", new ItemDto());
        return FORM;
    }

    @PostMapping("/add")
    public String addOrder(@Valid @ModelAttribute(name = "item") ItemDto item,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("item", item);
            return FORM;
        }

        try {
            String symbol = Utils.getCurrencySymbol(item.getCurrency());
            ItemDto newItem = itemService.addItem(item);
            model.addAttribute("item", newItem);
            model.addAttribute("symbol", symbol);
            List<OrderEntity> orders = newItem.getOrders();
            if (!orders.isEmpty()) {
                model.addAttribute("orders", orders);
                Map<Integer, CustomerDto> customers = new HashMap<>();
                for (OrderEntity order : orders) {
                    Integer customerId = order.getCustomerId();
                    CustomerDto customer = customerService.findById(customerId);
                    customers.put(customerId, customer);
                }
                model.addAttribute("customers", customers);
            }
            return RESULT;
        } catch (IllegalArgumentException ex) {
            String message = "This currency is not supported!";
            model.addAttribute("currNotValid", message);
            model.addAttribute("item", item);
            return FORM;
        }
    }
}