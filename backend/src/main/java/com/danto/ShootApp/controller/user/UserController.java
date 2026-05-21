package com.danto.ShootApp.controller.user;

import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;
import com.danto.ShootApp.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<CreateUserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{email}")
    public CreateUserResponse findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }


}
