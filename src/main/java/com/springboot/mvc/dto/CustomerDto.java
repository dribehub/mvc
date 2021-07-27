package com.springboot.mvc.dto;

import com.springboot.mvc.util.ValidationUtil;

import javax.validation.constraints.Pattern;

public class CustomerDto {

    private Integer id;
    @Pattern(regexp = ValidationUtil.NAME_REGEX, message="First name is invalid!")
    private String firstName;
    @Pattern(regexp = ValidationUtil.NAME_REGEX, message="Last name is invalid!")
    private String lastName;
    @Pattern(regexp = ValidationUtil.EMAIL_REGEX, message = "Email is invalid!")
    private String email;

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
        this.firstName = ValidationUtil.capitalizeFirst(firstName);
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = ValidationUtil.capitalizeFirst(lastName);
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}