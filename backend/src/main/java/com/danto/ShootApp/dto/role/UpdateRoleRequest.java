package com.danto.ShootApp.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRoleRequest(@NotNull(message = ERR_MSG_ID) Long id, @NotBlank(message = ERR_MSG_NAME) String name, String description) {

    private static final String ERR_MSG_ID = "ID can't be null !";
    private static final String ERR_MSG_NAME = "Name can't be empty !";

}
