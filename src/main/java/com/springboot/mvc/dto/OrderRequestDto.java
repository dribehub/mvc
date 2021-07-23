package com.springboot.mvc.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRequestDto {

    private Integer customerId;
    private Map<ItemDto, Boolean> items;
    public final Boolean TRUE = true;

    // [Failed to convert value of type 'java.lang.String' to required type 'java.util.List';
    // nested exception is java.lang.IllegalStateException:
    // Cannot convert value of type 'java.lang.String' to required type 'com.springboot.mvc.dto.ItemDto':
    // no matching editors or conversion strategy found]]

    public OrderRequestDto(List<ItemDto> items) {
        this.items = new HashMap<>();
        for (ItemDto item : items)
            this.items.put(item, false);
    }

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Map<ItemDto, Boolean> getItems() {
        return items;
    }
    public void setItems(Map<ItemDto, Boolean> items) {
        this.items = items;
    }
}
