package com.springboot.mvc.dto;

import com.springboot.mvc.entity.ItemEntity;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {

    private Integer id;
    private LocalDate date;
    private Integer customerId;
    private List<ItemEntity> items;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public List<ItemEntity> getItems() {
        return items;
    }
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
