package com.springboot.mvc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
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

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; }
    public String getCurrency() { return currency; }
    public List<OrderEntity> getOrders() { return orders; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(Double price) { this.price = price; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setOrders(List<OrderEntity> orders) { this.orders = orders; }
}
