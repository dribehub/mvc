package com.springboot.mvc.util;

import com.springboot.mvc.dto.ItemDto;

import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    
    public static final String
            NAME_REGEX = "^[A-Z][a-z]+$",
            MULT_NAMES_REGEX = "^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$",
            EMAIL_REGEX = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z]+\\.[a-z]+$",
            CURRENCY_REGEX = "^[A-Z]{3}$";

    public static String capFirst(String str) {
        if (str.contains(" ")) {
            String[] words = str.split(" ");
            Arrays.setAll(words, i -> capFirst(words[i]));
            return String.join(" ", words);
        }
        return Character.toUpperCase(str.charAt(0))
                + str.substring(1).toLowerCase();
    }

    public static String capAll(String str) {
            return str.toUpperCase();
    }

    public static String getCurrencySymbol(String currency) throws IllegalArgumentException {
        return Currency.getInstance(currency).getSymbol();
    }

    public static Map<String, String> getAllSymbols(List<ItemDto> items) {
        List<String> currencies = items.stream()
                .map(ItemDto::getCurrency)
                .collect(Collectors.toList());
        Map<String, String> symbols = new HashMap<>();
        for (String curr : currencies) {
            String symbol = getCurrencySymbol(curr);
            symbols.put(curr, symbol);
        }
        return symbols;
    }
}
