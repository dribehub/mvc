package com.springboot.mvc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageDto {

    private Integer id;
    private String text;
    private Integer senderId;
    private Integer receiverId;
}
