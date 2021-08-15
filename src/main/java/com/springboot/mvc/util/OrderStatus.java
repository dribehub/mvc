package com.springboot.mvc.util;

public enum OrderStatus {

    PENDING(1),
    APPROVED(2),
    ARRIVED(3);

    private final Integer code;
    private final String value;

    OrderStatus(Integer code) {
        this.code = code;
        this.value = Utils.capFirst(name());
    }

    public Integer code() { return code; }
    public String value() { return value; }
}
