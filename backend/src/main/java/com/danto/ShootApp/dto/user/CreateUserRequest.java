package com.danto.ShootApp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CreateUserRequest(@NotBlank(message = ERROR_MSG_NAME) String name, @NotBlank(message = ERROR_MSG_SNAME) String surname, @Email @NotBlank(message = ERROR_MSG_EMAIL) String email, String phoneNumber) {

    private static final String ERROR_MSG_NAME = "Name can't be empty !";
    private static final String ERROR_MSG_SNAME = "Surname can't be empty !";
    private static final String ERROR_MSG_EMAIL = "E-mail can't be empty !";



}

