package com.springboot.mvc.entity;

import com.springboot.mvc.util.Utils;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    private String name;

    public CategoryEntity() {}
    public CategoryEntity(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = Utils.capFirst(name); }
}
