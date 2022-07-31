package com.hrmelo.service.repository;

import com.hrmelo.service.model.dto.UserDto;

public interface UserRepository {

    UserDto findByUsername(String username);

    UserDto save(UserDto userDto);
}
