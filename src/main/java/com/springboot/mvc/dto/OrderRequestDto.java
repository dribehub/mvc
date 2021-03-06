package com.springboot.mvc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class OrderRequestDto {

    private Integer customerId;
    private Map<Integer, Boolean> itemIds;

    public OrderRequestDto() {}

    public OrderRequestDto(List<ItemDto> items) {
        itemIds = new HashMap<>();
        for (ItemDto item : items)
            itemIds.put(item.getId(), false);
    }
}
