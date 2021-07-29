package com.springboot.mvc.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRequestDto {

    private Integer customerId;
    private Map<Integer, Boolean> itemIds;

    public OrderRequestDto() {}
    public OrderRequestDto(List<ItemDto> items) {
        itemIds = new HashMap<>();
        for (ItemDto item : items)
            itemIds.put(item.getId(), false);
    }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public Map<Integer, Boolean> getItemIds() { return itemIds; }
    public void setItemIds(Map<Integer, Boolean> itemIds) { this.itemIds = itemIds; }
}
