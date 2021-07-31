package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CategoriesRequestDto;
import com.springboot.mvc.entity.CategoryEntity;
import com.springboot.mvc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private static final String LIST = "category/list";

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String getAll(Model model) {
        List<CategoryEntity> categories = categoryService.selectAll();
        CategoriesRequestDto ctgEdits = new CategoriesRequestDto(categories);
        model.addAttribute("ctgEdits", ctgEdits);
        return LIST;
    }
}
