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
        CategoriesRequestDto categories = categoryService.selectAllToDto();
        model.addAttribute("ctgEdits", categories);
        model.addAttribute("newCtg", new CategoryDto());
        return LIST;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "newCtg") @Valid CategoryDto newCtg,
                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            CategoriesRequestDto categories = categoryService.selectAllToDto();
            model.addAttribute("ctgEdits", categories);
            return LIST;
        }

        try {
            categoryService.add(newCtg);
        } catch (NonUniqueResultException ex) {
            String name = ex.getMessage();
            String message = String.format("Category \"%s\" is already inserted!", name);
            model.addAttribute("nonUniqueCtgError", message);
        }

        return getAll(model);
    }

    @PutMapping("/update")
    public String update(@ModelAttribute(name = "ctgEdits") @Valid CategoriesRequestDto edits,
                         BindingResult result, Model model) {
        if (result.hasErrors()) return LIST;

        List<CategoryDto> currentCtgs = categoryService.selectAll();
        List<CategoryDto> updatedCtgs = edits.getCategories();

        for (int i = 0; i < currentCtgs.size(); i++) {
            CategoryDto current = currentCtgs.get(i);
            CategoryDto updated = updatedCtgs.get(i);
            if (edits.isDeleted(i)) {
                categoryService.delete(updated);
                System.out.println(i);
            } else if (!current.equals(updated)) {
                try {
                    categoryService.update(current, updated);
                } catch (NonUniqueResultException ex) {
                    String attr = "nonUniqueCtgError" + i;
                    String name = ex.getMessage();
                    String message = String.format("Category \"%s\" is already present!", name);
                    model.addAttribute(attr, message);
                }
            }
        }

        return getAll(model);
    }
}
