package com.springboot.mvc.util;

import java.util.Arrays;

public enum OrderStatus {

    PENDING(1, "Pending"),
    APPROVED(2, "Approved"),
    ARRIVED(3, "Arrived");

    private final Integer code;
    private final String value;

    OrderStatus(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer code() { return code; }
    public String value() { return value; }

    public static String[] getAllStatuses() {
        return Arrays.stream(values())
                .map(OrderStatus::value)
                .toArray(String[]::new);
    }
}
