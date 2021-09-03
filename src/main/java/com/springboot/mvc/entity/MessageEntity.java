package com.springboot.mvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Getter @Setter
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private Integer senderId;
    private Integer receiverId;
}
