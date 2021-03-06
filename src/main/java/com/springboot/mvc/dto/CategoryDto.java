package com.springboot.mvc.dto;

import com.springboot.mvc.util.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter @Setter
public class CategoryDto {

    @Pattern(regexp = Utils.MULT_NAMES_REGEX, message = "Category name is invalid!")
    private String name;
    private Long numOfItems;

    public CategoryDto() {}

    public CategoryDto(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = Utils.capFirst(name);
    }

    public boolean equals(CategoryDto o) {
        if (this == o) return true;
        return name.equals(o.name);
    }

    public boolean equals(String o) {
        return name.equals(o);
    }
}
