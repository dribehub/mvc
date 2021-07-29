package com.springboot.mvc.dto;

import com.springboot.mvc.entity.OrderEntity;
import com.springboot.mvc.util.Utils;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class ItemDto {

    private Integer id;
    @Pattern(regexp = Utils.MULT_NAMES_REGEX, message="Item name is invalid!")
    private String name;
    @Pattern(regexp = Utils.MULT_NAMES_REGEX, message="Category is invalid!")
    private String category;
    private Double price;
    @Pattern(regexp = Utils.CURRENCY_REGEX, message="Currency is invalid!")
    private String currency;
    private List<OrderEntity> orders = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = Utils.capFirst(name); }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = Utils.capFirst(category); }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = Utils.capAll(currency); }
    public List<OrderEntity> getOrders() { return orders; }
    public void setOrders(List<OrderEntity> orders) { this.orders = orders; }
    public String getSymbol() { return Currency.getInstance(currency).getSymbol(); }
    public String getFullPrice() { return getSymbol() + " " + price; }
}
