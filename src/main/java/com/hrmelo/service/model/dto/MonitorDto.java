package com.hrmelo.service.model.dto;

public class MonitorDto {

    private String message;

    public MonitorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum OperationEnum {
        INSERTED,
        UPDATED
    }
}
