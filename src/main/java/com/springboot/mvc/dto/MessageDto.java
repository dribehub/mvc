package com.springboot.mvc.dto;

public class MessageDto {

    private Integer id;
    private String text;
    private Integer senderId;
    private Integer receiverId;

    public Integer getId() { return id; }
    public String getText() { return text; }
    public Integer getSenderId() { return senderId; }
    public Integer getReceiverId() { return receiverId; }
    public void setId(Integer id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setSenderId(Integer senderId) { this.senderId = senderId; }
    public void setReceiverId(Integer receiverId) { this.receiverId = receiverId; }
}
