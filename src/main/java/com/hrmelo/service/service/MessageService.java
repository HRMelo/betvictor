package com.hrmelo.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hrmelo.service.domain.Message;
import com.hrmelo.service.model.dto.MessageDto;
import com.hrmelo.service.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final UserService userService;

    private final WebSocketService webSocketService;

    public MessageService(UserService userService, WebSocketService webSocketService) {
        this.userService = userService;
        this.webSocketService = webSocketService;
    }

    public void send(Message message) throws JsonProcessingException {
        UserDto userDto = new UserDto(message.getUsername(), new MessageDto(message.getText()));
        userService.save(userDto);
        webSocketService.send(message);
    }
}
