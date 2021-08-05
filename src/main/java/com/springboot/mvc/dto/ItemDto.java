package com.springboot.mvc.dto;

import com.springboot.mvc.entity.OrderEntity;
import com.springboot.mvc.util.Utils;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class ItemDto {

    private Integer id;
    @Pattern(regexp = Utils.MULT_NAMES_REGEX, message = "Item name is invalid!")
    private String name;
    @Pattern(regexp = Utils.MULT_NAMES_REGEX, message = "Category is invalid!")
    private String category;
    private Double price;
    @Pattern(regexp = Utils.CURRENCY_REGEX, message = "Currency is invalid!")
    private String currency;
    private List<OrderEntity> orders = new ArrayList<>();

    public ItemDto() {}
    public ItemDto(ItemDto o) {
        id = o.id;
        name = o.name;
        category = o.category;
        price = o.price;
        currency = o.currency;
        orders = o.orders;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; }
    public String getCurrency() { return currency; }
    public List<OrderEntity> getOrders() { return orders; }
    public String getSymbol() { return Utils.getSymbol(currency); }
    public String getFullPrice() { return getSymbol() + " " + price; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = Utils.capFirst(name); }
    public void setCategory(String category) { this.category = Utils.capFirst(category); }
    public void setCategory(CategoryDto category) { this.category = Utils.capFirst(category); }
    public void setPrice(Double price) { this.price = price; }
    public void setCurrency(String currency) { this.currency = Utils.capAll(currency); }
    public void setOrders(List<OrderEntity> orders) { this.orders = orders; }

    public boolean equals(ItemDto o) {
        if (this == o) return true;
        return id.equals(o.id)
            && name.equals(o.name)
            && category.equals(o.category)
            && price.equals(o.price)
            && currency.equals(o.currency)
            && orders.equals(o.orders);
    }
    public boolean equalsLogically(ItemDto o) {
        return this == o || name.equals(o.name) && category.equals(o.category);
    }
}
