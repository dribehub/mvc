package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.dto.CategoryUpdateDto;
import com.springboot.mvc.service.CategoryService;
import com.springboot.mvc.service.ItemService;
import com.springboot.mvc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private static final String LIST = "category/list";

    private final AuthController auth;
    private final CategoryService categoryService;
    private final ItemService itemService;

    @Autowired
    public CategoryController(AuthController auth, CategoryService categoryService,
                              ItemService itemService) {
        this.auth = auth;
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        addLoggedInUser(model);
        List<CategoryDto> categories = categoryService.selectAll();
        for (CategoryDto ctg : categories)
            ctg.setNumOfItems(itemService.getNumOfItems(ctg));
        model.addAttribute("newCtg", new CategoryUpdateDto());
        model.addAttribute("categories", categories);
        return LIST;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "newCtg") @Valid CategoryDto newCtg,
                      BindingResult result, Model model) {
        addLoggedInUser(model);
        if (result.hasErrors()) return LIST;
        if (categoryService.exists(newCtg)) { // if the new category is already present
            // do not add category, throw error instead
            model.addAttribute("nonUniqueCtgError", Utils.CtgNotUnique(newCtg));
        } else { // else add the new category
            categoryService.add(newCtg);
        }
        return getAll(model);
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "newCtg") CategoryUpdateDto newCtg,
                         BindingResult result, Model model) {
        if (result.hasErrors()) return LIST;
        CategoryDto currentCtg = new CategoryDto(newCtg.getCurrent());
        CategoryDto updatedCtg = new CategoryDto(newCtg.getUpdated());
        if (categoryService.exists(updatedCtg)) {
            model.addAttribute(("nonUniqueCtgError"), Utils.CtgNotUnique(currentCtg));
        } else { // else, update category
            categoryService.add(updatedCtg);
            itemService.updateCategory(currentCtg, updatedCtg);
            categoryService.delete(currentCtg);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete")
    public String delete(@ModelAttribute(name = "deletedCtg") CategoryDto deletedCtg) {
        categoryService.delete(deletedCtg);
        return "redirect:/categories";
    }

    private void addLoggedInUser(Model model) {
        model.addAttribute("user", auth.getLoggedInUser());
    }
}
