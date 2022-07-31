package com.hrmelo.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmelo.service.domain.Message;
import com.hrmelo.service.model.dto.MonitorDto;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final MessageSendingOperations<String> messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @SendTo("/topic/messages")
    public void send(Message message) throws JsonProcessingException {
        String json = (new ObjectMapper()).writeValueAsString(message);
        messagingTemplate.convertAndSend("/topic/messages", json);
    }

    public void send(MonitorDto monitorDto) throws JsonProcessingException {
        String json = (new ObjectMapper()).writeValueAsString(monitorDto);
        messagingTemplate.convertAndSend("/topic/monitor", json);
    }
}
