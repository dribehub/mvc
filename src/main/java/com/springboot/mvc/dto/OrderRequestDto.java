package com.springboot.mvc.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRequestDto {

    private Integer customerId;
    private Map<Integer, String> itemsIds;

    public OrderRequestDto() {}
    public OrderRequestDto(List<ItemDto> items) {
        itemsIds = new HashMap<>();
        for (ItemDto item : items)
            itemsIds.put(item.getId(), "false");
    }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public Map<Integer, String> getItemsIds() { return itemsIds; }
    public void setItemsIds(Map<Integer, String> itemsIds) { this.itemsIds = itemsIds; }
}
