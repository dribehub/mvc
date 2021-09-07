package com.springboot.mvc.util;

import com.springboot.mvc.dto.CategoryDto;
import com.springboot.mvc.dto.ItemDto;

import java.text.DecimalFormat;
import java.time.LocalDate;
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

    public static final String
            CURRENCY_NOT_SUPPORTED = "This currency is not supported!",
            EMAIL_NOT_UNIQUE = "This email already exists!",
            EMAIL_NOT_FOUND = "This email doesn't exist!",
            ORDER_NOT_FOUND = "Requested order could not be found!",
            ITEM_NOT_FOUND = "Requested item could not be found!",
            CTG_HAS_ITEMS = "Category already has items and cannot be deleted!",
            INVALID_PASS = "Password is incorrect!";

    public static String ItemNotUnique(ItemDto item) {
        return String.format("Item \"%s\" already exists!", item.getName());
    }
    public static String CtgNotUnique(CategoryDto category) {
        return String.format("Category \"%s\" already exists!", category.getName());
    }

    public static String capFirst(String str) {
        if (str.contains(" ")) {
            String[] words = str.split(" ");
            Arrays.setAll(words, i -> capFirst(words[i]));
            return String.join(" ", words);
        }
        return Character.toUpperCase(str.charAt(0))
                + str.substring(1).toLowerCase();
    }
    public static String capFirst(CategoryDto category) {
        return capFirst(category.getName());
    }
    public static String capAll(String str) {
        return str.toUpperCase();
    }
    public static String convertToLongDate(LocalDate localDate) {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        String[] info = localDate.toString().split("-");
        int monthIndex = Integer.parseInt(info[1]) - 1;
        return String.format("%s %s %s", info[2], months[monthIndex], info[0]);
    }
    public static String removeTrailingZero(Double decimal) {
        return new DecimalFormat("0.#####").format(decimal);
    }

    public static Boolean isSupported(String currency) {
        try {
            Currency.getInstance(currency);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public static Boolean isCurrencySupported(ItemDto item) {
        return isSupported(item.getCurrency());
    }
    public static String getSymbol(String currency) throws IllegalArgumentException {
        return Currency.getInstance(currency).getSymbol();
    }
    public static String getCurrencySymbol(ItemDto item) throws IllegalArgumentException {
        return getSymbol(item.getCurrency());
    }
    public static Map<String, String> getAllSymbols(List<ItemDto> items) {
        List<String> currencies = items.stream()
                .map(ItemDto::getCurrency)
                .collect(Collectors.toList());
        Map<String, String> symbols = new HashMap<>();
        for (String curr : currencies) {
            String symbol = getSymbol(curr);
            symbols.put(curr, symbol);
        }
        return symbols;
    }
}
