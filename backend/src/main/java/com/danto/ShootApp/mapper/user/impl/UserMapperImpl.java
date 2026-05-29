package com.danto.ShootApp.mapper.user.impl;

import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;
import com.danto.ShootApp.entity.user.UserEntity;
import com.danto.ShootApp.mapper.user.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    // (dto)-->(entity)
    public UserEntity toEntity(CreateUserRequest request) {
        return new UserEntity(null, request.name(), request.surname(), request.email(), request.phoneNumber(), null);
    }

    @Override
    // (entity)-->(dto)
    public CreateUserResponse toResponse(UserEntity user) {
        return new CreateUserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getPhoneNumber());
    }


}
