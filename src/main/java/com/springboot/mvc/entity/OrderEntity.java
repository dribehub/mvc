package com.springboot.mvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Integer customerId;
    private Integer status;
    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private List<ItemEntity> items;
}
