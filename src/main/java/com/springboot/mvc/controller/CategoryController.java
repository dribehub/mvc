package com.springboot.mvc.controller;

import com.springboot.mvc.dto.CategoriesDto;
import com.springboot.mvc.dto.CategoryDto;
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
        CategoriesDto ctgEdits = categoryService.selectAllToDto();
        for (CategoryDto ctg : ctgEdits.getCategories())
            ctg.setNumOfItems(itemService.getNumOfItems(ctg));
        model.addAttribute("ctgEdits", ctgEdits);
        model.addAttribute("newCtg", new CategoryDto());
        return LIST;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "newCtg") @Valid CategoryDto newCtg,
                      BindingResult result, Model model) {
        addLoggedInUser(model);
        if (result.hasErrors()) {
            model.addAttribute("ctgEdits", categoryService.selectAllToDto());
            return LIST;
        }

        if (categoryService.exists(newCtg)) { // if the new category is already present
            // do not add category, throw error instead
            model.addAttribute("nonUniqueCtgError", Utils.CtgNotUnique(newCtg));
        } else { // else add the new category
            categoryService.add(newCtg);
        }

        return getAll(model);
    }

    @PutMapping("/update")
    public String update(@ModelAttribute(name = "ctgEdits") @Valid CategoriesDto edits,
                         BindingResult result, Model model) {
        if (result.hasErrors()) return LIST;

        List<CategoryDto> currentCtgs = categoryService.selectAll();
        List<CategoryDto> updatedCtgs = edits.getCategories();

        for (int i = 0; i < currentCtgs.size(); i++) {
            CategoryDto current = currentCtgs.get(i);
            CategoryDto updated = updatedCtgs.get(i);
            if (edits.isDeleted(i)) { // if category has been deleted
                if (itemService.contains(current)) { // and there are items of this category
                    // do not delete category, throw error instead
                    model.addAttribute(("isPresentError" + i), Utils.CTG_HAS_ITEMS);
                } else { // else, delete category
                    categoryService.delete(current);
                }
            } else if (!current.equals(updated)) { // if category has been edited
                if (categoryService.exists(updated)) { // and the new category already exists
                    // do not update category, throw error instead
                    model.addAttribute(("nonUniqueCtgError" + i), Utils.CtgNotUnique(current));
                } else { // else, update category
                    categoryService.add(updated);
                    itemService.updateCategory(current, updated);
                    categoryService.delete(current);
                }
            }
        }

        return getAll(model);
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "current") String current,
                         @ModelAttribute(name = "updated") String updated,
                         BindingResult result, Model model) {
        if (result.hasErrors()) return LIST;
        CategoryDto currentCtg = new CategoryDto(current);
        CategoryDto updatedCtg = new CategoryDto(updated);
        if (categoryService.exists(updatedCtg)) {
            model.addAttribute(("nonUniqueCtgError"), Utils.CtgNotUnique(currentCtg));
        } else { // else, update category
            categoryService.add(updatedCtg);
            itemService.updateCategory(currentCtg, updatedCtg);
            categoryService.delete(currentCtg);
        }
        return "redirect:/";
    }

    private void addLoggedInUser(Model model) {
        model.addAttribute("user", auth.getLoggedInUser());
    }
}
