package com.danto.ShootApp.controller.user;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;
import com.danto.ShootApp.dto.user.UpdateUserRequest;
import com.danto.ShootApp.service.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping
    public List<CreateUserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/email/{email}")
    public CreateUserResponse findUserByEmail(@Email @PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping(path = "/{id}")
    public CreateUserResponse findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping
    public CreateUserResponse updateUser(@Valid @RequestBody UpdateUserRequest updateRequest) {
        return userService.fullUserUpdate(updateRequest);
    }

    @DeleteMapping(path = "/{id}")
    public DeleteResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
