package com.hrmelo.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hrmelo.service.domain.Message;
import com.hrmelo.service.domain.User;
import com.hrmelo.service.model.dto.MonitorDto;
import com.hrmelo.service.model.dto.UserDto;
import com.hrmelo.service.repository.UserRepository;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper mapper;

    public UserService(UserRepository repository, WebSocketService webSocketService, ModelMapper mapper) {
        this.repository = repository;
        this.webSocketService = webSocketService;
        this.mapper = mapper;
    }

    public void save(UserDto userDto) throws JsonProcessingException {
        UserDto userDtoFound = repository.findByUsername(userDto.getUsername());
        User userFound = mapper.map(userDtoFound, User.class);

        Message newMessage = mapper.map(userDto.getMessages().get(0), Message.class);
        userFound.addMessage(newMessage);

        if(!StringUtils.hasText(userFound.getId())) {
            userFound.setUsername(userDto.getUsername());
        }

        UserDto userToSave = mapper.map(userFound, UserDto.class);
        final UserDto userSaved = repository.save(userToSave);

        final MonitorDto monitorDto = new MonitorDto(format(
                MESSAGE,
                Instant.now(),
                userSaved.getId(),
                !StringUtils.hasText(userFound.getId()) ? INSERTED : UPDATED));
        webSocketService.send(monitorDto);
    }
}
