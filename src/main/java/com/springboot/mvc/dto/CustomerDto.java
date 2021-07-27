package com.springboot.mvc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CustomerDto {

    private Integer id;
    @Pattern(regexp = "^[A-Z][a-z]+$", message="First name is invalid!")
    private String firstName;
    @Pattern(regexp = "^[A-Z][a-z]+$", message="Last name is invalid!")
    private String lastName;
    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z]+\\.[A-Za-z]+$",
            message = "Email is invalid!")
    private String email;

    private static String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0))
                + name.substring(1).toLowerCase();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = capitalize(firstName);
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = capitalize(lastName);
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}