package com.springboot.mvc.util;

public enum OrderStatus {

    PENDING(1),
    APPROVED(2),
    ARRIVED(3);

    private final Integer code;

    OrderStatus(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
