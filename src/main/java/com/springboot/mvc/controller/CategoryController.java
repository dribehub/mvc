package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CategoriesRequestDto;
import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.service.CategoryService;
import com.springboot.mvc.service.ItemService;
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
    private final ItemService itemService;

    @Autowired
    public CategoryController(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
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
            if (edits.isDeleted(i)) { // if category has been deleted
                if (itemService.contains(current)) { // and there are items of this category
                    // do not delete category, throw error
                    String message = "Category already has items and cannot be deleted!";
                    model.addAttribute(("isPresentError" + i), message);
                } else { // else, delete category
                    categoryService.delete(updated);
                }
            } else if (!current.equals(updated)) { // if category has been edited
                /*try { // try updating category
                    itemService.updateCategory(current, updated);
                    categoryService.update(current, updated);
                } catch (NonUniqueResultException ex) { // if duplicates occur
                    // do not update category, throw error
                    String name = ex.getMessage();
                    String message = String.format("Category \"%s\" is already present!", name);
                    model.addAttribute(("nonUniqueCtgError" + i), message);
                }*/
                if (categoryService.isPresent(updated)) {
                    String name = updated.getName();
                    String message = String.format("Category \"%s\" is already present!", name);
                    model.addAttribute(("nonUniqueCtgError" + i), message);
                } else {
                    categoryService.add(updated);
                    itemService.updateCategory(current, updated);
                    categoryService.delete(current);
                }
            }
        }

        return getAll(model);
    }
}
