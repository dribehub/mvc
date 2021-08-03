package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CategoriesRequestDto;
import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private static final String LIST = "category/list";
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        CategoriesRequestDto ctgEdits = categoryService.selectAllToDto();
        model.addAttribute("ctgEdits", ctgEdits);
        model.addAttribute("newCtg", new CategoryDto());
        return LIST;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "newCtg") @Valid CategoryDto newCtg,
                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ctgEdits", categoryService.selectAllToDto());
            return LIST;
        }

        try {
            categoryService.addCategory(newCtg);
        } catch (NonUniqueResultException ex) {
            model.addAttribute("nonUniqueCtgError", ex.getMessage());
        }

        return getAll(model);
    }

    @PutMapping("/update")
    public String update(@ModelAttribute(name = "ctgEdits") @Valid CategoriesRequestDto edits,
                         BindingResult result, Model model) {

        return LIST;
    }
}
