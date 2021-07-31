package com.springboot.mvc.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
