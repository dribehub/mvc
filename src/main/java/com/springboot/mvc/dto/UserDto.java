package com.springboot.mvc.dto;

import com.springboot.mvc.util.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
public class UserDto {

    private Integer id;
    private String role;
    @Pattern(regexp = Utils.NAME_REGEX, message = "First name is invalid!")
    private String firstName;
    @Pattern(regexp = Utils.NAME_REGEX, message = "Last name is invalid!")
    private String lastName;
    @Pattern(regexp = Utils.EMAIL_REGEX, message = "Email is invalid!")
    private String email;
    @Size(min = 8, message = "Password is too short!")
    private String password;

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Utils.capFirst(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = Utils.capFirst(lastName);
    }

    public boolean equals(UserDto customer) {
        return customer == this
                || customer != null
                && customer.email.equals(email)
                && customer.password.equals(password);
    }
}