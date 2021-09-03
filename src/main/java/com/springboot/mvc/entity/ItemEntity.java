package com.springboot.mvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Getter @Setter
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private Double price;
    private String currency;
    @ManyToMany(mappedBy = "items")
    private List<OrderEntity> orders;
}
