package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.dto.UpdateDto;
import com.springboot.mvc.dto.UserDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.entity.OrderEntity;
import com.springboot.mvc.service.CategoryService;
import com.springboot.mvc.service.UserService;
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

    private final AuthController auth;
    private final ItemService itemService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ItemController(AuthController auth, ItemService itemService,
                          UserService userService, CategoryService categoryService) {
        this.auth = auth;
        this.itemService = itemService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        addLoggedInUser(model);
        List<ItemDto> items = itemService.selectAll();
        Map<String, String> symbols = Utils.getAllSymbols(items);
        List<CategoryDto> categories = categoryService.selectAll();
        model.addAttribute("items", items);
        model.addAttribute("symbols", symbols);
        model.addAttribute("categories", categories);
        model.addAttribute("updateItem", new ItemDto());
        return ITEM_LIST;
    }

    @GetMapping("/{id}") // TODO: to be deleted
    public String getById(Model model, @PathVariable(value = "id") Integer id) {
        ItemDto item = itemService.findById(id);
        if (item == null) {
            model.addAttribute("error", Utils.ITEM_NOT_FOUND);
            return ERROR;
        } else {
            addLoggedInUser(model);
            getItemData(model, item);
            return ITEM_BY_ID;
        }
    }

    @GetMapping("/create") // TODO: to be deleted
    public String createForm(Model model) {
        addLoggedInUser(model);
        List<CategoryDto> categories = categoryService.selectAll();
        model.addAttribute("categories", categories);
        model.addAttribute("item", new ItemDto());
        return FORM;
    }

    @PostMapping("/add") // TODO: to be deleted
    public String add(@ModelAttribute(name = "item") @Valid ItemDto item,
                          BindingResult result, Model model) {
        addLoggedInUser(model);
        if (result.hasErrors()) return FORM;

        if (itemService.isUnique(item)) {
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

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "updateItem") ItemDto updated) {
        fillOut(updated);
        itemService.update(updated);
        return "redirect:/items";
    }

    @RequestMapping(value = "/{id}/delete")
    public String deleteById(@PathVariable(value = "id") Integer id) {
        itemService.deleteById(id);
        return "redirect:/items";
    }

    private void getItemData(Model model, ItemDto item) {
        String symbol = Utils.getCurrencySymbol(item);
        List<OrderEntity> orders = item.getOrders();
        model.addAttribute("item", item);
        model.addAttribute("symbol", symbol);

        if (!orders.isEmpty()) {
            System.out.println(orders.size());
            Map<Integer, UserDto> customers = new HashMap<>();
            for (OrderEntity order : orders) {
                Integer customerId = order.getCustomerId();
                UserDto customer = userService.findById(customerId);
                customers.put(customerId, customer);
            }

            model.addAttribute("orders", orders);
            model.addAttribute("customers", customers);
        }
    }

    private void fillOut(ItemDto updated) {
        ItemDto current = itemService.findById(updated.getId());
        if (updated.getName() == null)
            updated.setName(current.getName());
        if (updated.getCategory() == null)
            updated.setCategory(current.getCategory());
        if (updated.getPrice() == null)
            updated.setPrice(current.getPrice());
        if (updated.getCurrency() == null)
            updated.setCurrency(current.getCurrency());
    }

    private void addLoggedInUser(Model model) {
        model.addAttribute("user", auth.getLoggedInUser());
    }
}