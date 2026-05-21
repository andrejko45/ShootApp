package com.danto.ShootApp.service.user;

import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;


import java.util.List;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);
    List<CreateUserResponse> getUsers();
    CreateUserResponse findUserByEmail(String email);
    CreateUserResponse findUserById(Long id);

}
