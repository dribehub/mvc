package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CategoriesRequestDto;
import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.entity.CategoryEntity;
import com.springboot.mvc.service.CategoryService;
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
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<CategoryEntity> categories = categoryService.selectAll();
        model.addAttribute("newCtg", new CategoryDto());
        model.addAttribute("ctgEdits", new CategoriesRequestDto(categories));
        return LIST;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "newCtg")
                      @Valid CategoryDto newCtg,
                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<CategoryEntity> categories = categoryService.selectAll();
            model.addAttribute("ctgEdits", new CategoriesRequestDto(categories));
            return LIST;
        }
        categoryService.addCategory(newCtg);
        return "redirect:/categories";
    }

    @PutMapping("/update")
    public String update(@ModelAttribute(name = "ctgEdits")
                         @Valid CategoriesRequestDto edits,
                         BindingResult result, Model model) {
        return LIST;
    }
}
