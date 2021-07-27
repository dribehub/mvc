package com.springboot.mvc.dto;

import com.springboot.mvc.entity.OrderEntity;
import com.springboot.mvc.util.ValidationUtil;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class ItemDto {

    private Integer id;
    @Pattern(regexp = ValidationUtil.NAME_REGEX, message="Item name is invalid!")
    private String name;
    @Pattern(regexp = ValidationUtil.NAME_REGEX, message="Category is invalid!")
    private String category;
    @Min(value = 0)
    private Double price;
    @Pattern(regexp = ValidationUtil.CURR_REGEX, message="Currency is invalid!")
    private String currency;
    private List<OrderEntity> orders;

    public ItemDto() {
        orders = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = ValidationUtil.capitalizeFirst(name);
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = ValidationUtil.capitalizeFirst(category);
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = ValidationUtil.capitalizeAll(currency);
    }
    public List<OrderEntity> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
