package com.springboot.mvc.mapper;

import com.springboot.mvc.dto.MessageDto;
import com.springboot.mvc.entity.MessageEntity;

public class MessageMapper {

    public static MessageEntity toEntity(MessageDto dto) {
        MessageEntity entity = new MessageEntity();
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setSenderId(dto.getSenderId());
        entity.setReceiverId(dto.getReceiverId());
        return entity;
    }

    public static MessageDto toDto(MessageEntity entity) {
        MessageDto dto = new MessageDto();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setSenderId(entity.getSenderId());
        dto.setReceiverId(entity.getReceiverId());
        return dto;
    }
}
