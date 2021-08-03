package com.springboot.mvc.dto;

import com.springboot.mvc.util.Utils;

import javax.validation.constraints.Pattern;
import java.util.Objects;

public class CategoryDto {

    @Pattern(regexp = Utils.MULT_NAMES_REGEX, message = "Category name is invalid!")
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = Utils.capFirst(name); }

    public boolean equals(CategoryDto o) {
        if (this == o) return true;
        return name.equals(o.name);
    }
}
