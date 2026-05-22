package com.danto.ShootApp.service.user;

import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;
import com.danto.ShootApp.dto.user.DeleteResponse;
import com.danto.ShootApp.dto.user.UpdateUserRequest;


import java.util.List;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);
    List<CreateUserResponse> getUsers();
    CreateUserResponse findUserByEmail(String email);
    CreateUserResponse findUserById(Long id);
    CreateUserResponse fullUserUpdate(UpdateUserRequest updateUser);
    DeleteResponse deleteUser(Long id);
}
