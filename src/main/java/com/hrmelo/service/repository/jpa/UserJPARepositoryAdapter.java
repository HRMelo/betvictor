package com.hrmelo.service.repository.jpa;

import com.hrmelo.service.model.UserEntity;
import com.hrmelo.service.model.dto.UserDto;
import com.hrmelo.service.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserJPARepositoryAdapter implements UserRepository {

    private final UserJPARepository repository;

    private final ModelMapper mapper;

    public UserJPARepositoryAdapter(UserJPARepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto findByUsername(String username) {
        UserEntity user = repository.findByUsername(username).orElse(new UserEntity());
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity user = mapper.map(userDto, UserEntity.class);
        UserEntity userEntity = repository.save(user);
        return mapper.map(userEntity, UserDto.class);
    }
}
