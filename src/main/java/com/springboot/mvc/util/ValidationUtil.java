package com.springboot.mvc.util;

import com.springboot.mvc.dto.ItemDto;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationUtil {
    
    public static final String
            NAME_REGEX = "^[A-Z][a-z]+$",
            EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z]+\\.[A-Za-z]+$",
            CURR_REGEX = "^[A-Za-z]{3}$";

    public static String capitalizeFirst(String name) {
        return Character.toUpperCase(name.charAt(0))
                + name.substring(1).toLowerCase();
    }

    public static String capitalizeAll(String name) {
        return name.toUpperCase();
    }

    public static String getCurrencySymbol(String currency) throws IllegalArgumentException {
        return Currency.getInstance(currency).getSymbol();
    }

    public static Map<String, String> getMapOfSymbols(List<String> currencies) {
        Map<String, String> symbols = new HashMap<>();
        for (String curr : currencies) {
            String symbol = getCurrencySymbol(curr);
            symbols.put(curr, symbol);
        }
        return symbols;
    }

    public static Map<String, String> getItemsSymbols(List<ItemDto> items) {
        List<String> currencies = items.stream()
                .map(ItemDto::getCurrency)
                .collect(Collectors.toList());
        return getMapOfSymbols(currencies);
    }
}
