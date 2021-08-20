package com.springboot.mvc.dto;

public class MessageDto {

    private Integer userId;
    private String text;

    public Integer getUserId() { return userId; }
    public String getText() { return text; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public void setText(String text) { this.text = text; }
}
