package com.springboot.mvc.util;

public class ValidationUtil {
    
    public static final String
            NAME_REGEX = "^[A-Z][a-z]+$",
            EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z]+\\.[A-Za-z]+$";

    public static String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0))
                + name.substring(1).toLowerCase();
    }
}
