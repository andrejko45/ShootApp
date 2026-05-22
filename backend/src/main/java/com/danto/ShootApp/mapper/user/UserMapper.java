package com.danto.ShootApp.mapper.user;

import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;
import com.danto.ShootApp.dto.user.UpdateUserRequest;
import com.danto.ShootApp.entity.user.UserEntity;


public interface UserMapper {

    UserEntity toEntity(CreateUserRequest request);

    CreateUserResponse toResponse(UserEntity user);

}
