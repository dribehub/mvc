package com.springboot.mvc.service.impl;

import com.springboot.mvc.dto.MessageDto;
import com.springboot.mvc.dto.UserDto;
import com.springboot.mvc.entity.MessageEntity;
import com.springboot.mvc.mapper.MessageMapper;
import com.springboot.mvc.repository.MessageRepository;
import com.springboot.mvc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    @Autowired
    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override public List<MessageDto> selectAll() {
        return repository.findAll()
                .stream().map(MessageMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override public MessageDto findById(Integer id) {
        return repository.findById(id)
                .map(MessageMapper::toDto)
                .orElse(null);
    }
    @Override public MessageDto addMessage(MessageDto message) {
        MessageEntity entity = MessageMapper.toEntity(message);
        return MessageMapper.toDto(repository.save(entity));
    }
    @Override public void addMessageToMany(MessageDto message, UserDto[] users) {
        String text = message.getText();
        Integer senderId = message.getSenderId();
        for (UserDto user : users) {
            MessageDto messageDto = new MessageDto();
            messageDto.setText(text);
            messageDto.setSenderId(senderId);
            messageDto.setReceiverId(user.getId());
            addMessage(messageDto);
        }
    }
}