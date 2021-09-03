package com.springboot.mvc.entity;

import com.springboot.mvc.util.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter @Setter
public class CategoryEntity {

    @Id
    private String name;

    public void setName(String name) {
        this.name = Utils.capFirst(name);
    }
}
