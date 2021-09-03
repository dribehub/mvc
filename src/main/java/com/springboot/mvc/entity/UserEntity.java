package com.springboot.mvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
}