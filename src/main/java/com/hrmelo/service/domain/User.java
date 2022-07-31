package com.hrmelo.service.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String username;
    private List<Message> messages;

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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message messageDto) {
        if(null == messages) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(messageDto);
    }
}
