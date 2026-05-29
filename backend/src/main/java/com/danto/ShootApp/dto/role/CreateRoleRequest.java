package com.danto.ShootApp.dto.role;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleRequest(@NotBlank(message = ERR_MSG_NAME) String name, String title) {

    private static final String ERR_MSG_NAME = "Name can't be empty !";

}
