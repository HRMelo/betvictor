package com.hrmelo.service.model.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private String id;
    private String username;
    private List<MessageDto> messages;

    public UserDto() {
    }

    public UserDto(String username, MessageDto message) {
        this.username = username;
        this.messages = new ArrayList<>();
        this.messages.add(message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public void addMessage(MessageDto messageDto) {
        if(null == messages) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(messageDto);
    }
}
