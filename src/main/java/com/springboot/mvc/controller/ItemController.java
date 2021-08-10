package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.entity.OrderEntity;
import com.springboot.mvc.service.CategoryService;
import com.springboot.mvc.service.CustomerService;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.springboot.mvc.service.ItemService;
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

    private final ItemService itemService;
    private final CustomerService customerService;
    private final CategoryService categoryService;
    private CustomerDto loggedInUser;

    @Autowired
    public ItemController(ItemService itemService,
                          CustomerService customerService,
                          CategoryService categoryService) {
        this.itemService = itemService;
        this.customerService = customerService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        model.addAttribute("user", loggedInUser);
        List<ItemDto> items = itemService.selectAll();
        Map<String, String> symbols = Utils.getAllSymbols(items);
        model.addAttribute("items", items);
        model.addAttribute("symbols", symbols);
        return ITEM_LIST;
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(value = "id") Integer id) {
        ItemDto item = itemService.findById(id);
        if (item == null) {
            model.addAttribute("error", Utils.ITEM_NOT_FOUND);
            return ERROR;
        } else {
            getItemData(model, item);
            return ITEM_BY_ID;
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("user", loggedInUser);
        List<CategoryDto> categories = categoryService.selectAll();
        model.addAttribute("categories", categories);
        model.addAttribute("item", new ItemDto());
        return FORM;
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute(name = "item") @Valid ItemDto item,
                          BindingResult result, Model model) {
        if (result.hasErrors()) return FORM;

        if (itemService.isPresent(item)) {
            model.addAttribute("categories", categoryService.selectAll());
            model.addAttribute("nonUniqueItemError", Utils.ItemNotUnique(item));
            return FORM;
        }

        if (!Utils.isCurrencySupported(item)) {
            model.addAttribute("categories", categoryService.selectAll());
            model.addAttribute("currNotValid", Utils.CURRENCY_NOT_SUPPORTED);
            model.addAttribute("item", item);
            return FORM;
        }

        getItemData(model, itemService.add(item));
        return RESULT;
    }

    private void getItemData(Model model, ItemDto item) {
        model.addAttribute("user", loggedInUser);
        String symbol = Utils.getCurrencySymbol(item);
        List<OrderEntity> orders = item.getOrders();
        model.addAttribute("item", item);
        model.addAttribute("symbol", symbol);

        if (!orders.isEmpty()) {
            System.out.println(orders.size());
            Map<Integer, CustomerDto> customers = new HashMap<>();
            for (OrderEntity order : orders) {
                Integer customerId = order.getCustomerId();
                CustomerDto customer = customerService.findById(customerId);
                customers.put(customerId, customer);
            }

            model.addAttribute("orders", orders);
            model.addAttribute("customers", customers);
        }
    }

    @GetMapping("/user/{id}")
    public String redirect(@PathVariable(value = "id") Integer id) {
        loggedInUser = customerService.findById(id);
        return "redirect:/items";
    }
}