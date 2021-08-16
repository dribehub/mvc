package com.springboot.mvc.dto;

import java.time.LocalDate;
import java.util.List;

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

    public Integer getId() { return id; }
    public LocalDate getDate() { return date; }
    public Integer getCustomerId() { return customerId; }
    public Integer getStatus() { return status; }
    public List<ItemDto> getItems() { return items; }

    public void setId(Integer id) { this.id = id; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setStatus(Integer status) { this.status = status; }
    public void setItems(List<ItemDto> items) { this.items = items; }
}
