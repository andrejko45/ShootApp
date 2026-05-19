package com.danto.ShootApp.service.user;

import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.entity.user.UserEntity;

public interface UserService {

    UserEntity createUser(CreateUserRequest request);
}
