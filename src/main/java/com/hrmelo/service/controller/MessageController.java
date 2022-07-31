package com.hrmelo.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hrmelo.service.domain.Message;
import com.hrmelo.service.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/newUser")
    public Message greeting(Message message) throws JsonProcessingException {
        LOGGER.info(message.getUsername());
        messageService.send(new Message(message.getUsername(), "connected"));
        return new Message(message.getUsername(), "connected");
    }

    @MessageMapping("/message")
    public Message send(Message message) throws JsonProcessingException {
        messageService.send(new Message(message.getUsername(), message.getText()));
        return new Message(message.getUsername(), message.getText());
    }
}
