package com.hrmelo.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hrmelo.service.model.dto.MonitorDto;
import com.hrmelo.service.model.dto.UserDto;
import com.hrmelo.service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;

import static com.hrmelo.service.model.dto.MonitorDto.OperationEnum.INSERTED;
import static com.hrmelo.service.model.dto.MonitorDto.OperationEnum.UPDATED;
import static java.lang.String.format;

@Service
public class UserService {

    private static final String MESSAGE = "timestamp=%s :: a row with ID=%s was %s";

    private final UserRepository repository;

    private final WebSocketService webSocketService;

    public UserService(UserRepository repository, WebSocketService webSocketService) {
        this.repository = repository;
        this.webSocketService = webSocketService;
    }

    public void save(UserDto userDto) throws JsonProcessingException {
        UserDto user = repository.findByUsername(userDto.getUsername());
        MonitorDto.OperationEnum operation;
        if(!StringUtils.hasText(user.getId())) {
            operation = INSERTED;
            user.setUsername(userDto.getUsername());
        } else {
            operation = UPDATED;
        }

        user.addMessage(userDto.getMessages().get(0));

        final UserDto userSaved = repository.save(user);
        webSocketService.send(new MonitorDto(format(MESSAGE, Instant.now(), userSaved.getId(), operation)));
    }
}
