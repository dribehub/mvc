package com.springboot.mvc.dto;

import com.springboot.mvc.entity.ItemEntity;

import java.util.List;

public class OrderRequestDto {

    private Integer customerId;
    private List<ItemEntity> items;

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
