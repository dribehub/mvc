package com.springboot.mvc.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Integer customerId;
    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private List<ItemEntity> items;

    public Integer getId() { return id; }
    public LocalDate getDate() { return date; }
    public Integer getCustomerId() { return customerId; }
    public List<ItemEntity> getItems() { return items; }

    public void setId(Integer id) { this.id = id; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setItems(List<ItemEntity> items) { this.items = items; }
}
