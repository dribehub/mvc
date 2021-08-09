package com.springboot.mvc.dto;

import com.springboot.mvc.util.Utils;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerDto {

    private Integer id;
    private String role;
    @Pattern(regexp = Utils.NAME_REGEX, message = "First name is invalid!")
    private String firstName;
    @Pattern(regexp = Utils.NAME_REGEX, message = "Last name is invalid!")
    private String lastName;
    @Pattern(regexp = Utils.EMAIL_REGEX, message = "Email is invalid!")
    private String email;
    @Size(min = 8)
    private String password;

    public Integer getId() { return id; }
    public String getRole() {
        return role;
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getName() { return firstName + " " + lastName; }
    public String getPassword() { return password; }

    public void setId(Integer id) { this.id = id; }
    public void setRole(String role) {
        this.role = role;
    }
    public void setFirstName(String firstName) { this.firstName = Utils.capFirst(firstName); }
    public void setLastName(String lastName) { this.lastName = Utils.capFirst(lastName); }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    public boolean equals(CustomerDto customer) {
        return customer == this
            || customer != null
            && customer.email.equals(email)
            && customer.password.equals(password);
    }
}