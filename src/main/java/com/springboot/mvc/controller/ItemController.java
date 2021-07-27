package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CustomerDto;
import com.springboot.mvc.dto.ItemDto;
import com.springboot.mvc.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.springboot.mvc.service.IItemService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private static final String
            ITEM_LIST = "items",
            ITEM_BY_ID = "item",
            FORM = "create_item",
            RESULT = "item_result",
            ERROR = "error";

    @Autowired
    private IItemService itemService;

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<ItemDto> items = itemService.selectAll();
        model.addAttribute("items", items);
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
            model.addAttribute("item", item);
            return ITEM_BY_ID;
        }
    }
}