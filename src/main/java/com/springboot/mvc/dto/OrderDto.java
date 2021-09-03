package com.springboot.mvc.dto;

import com.springboot.mvc.util.Utils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class OrderDto {

    private Integer id;
    private LocalDate date;
    private Integer customerId;
    private Integer status;
    private List<ItemDto> items;

    public OrderDto() {}

    public OrderDto(OrderDto o) {
        id = o.id;
        date = o.date;
        customerId = o.customerId;
        status = o.status;
        items = o.items;
    }

    public String getLongDate() {
        return Utils.convertToLongDate(date);
    }
}
