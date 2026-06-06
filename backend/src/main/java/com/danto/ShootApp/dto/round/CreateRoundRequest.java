package com.danto.ShootApp.dto.round;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRoundRequest(@NotBlank(message = ERR_MSG_NAME) String name, @NotNull(message = ERR_MSG_COMP) Long compId,@NotNull(message = ERR_MSG_IND) @Min(value = 1, message = ERR_MSG_IND) Integer orderIndex, String description) {

    private static final String ERR_MSG_NAME = "Name can't be empty"; 
    private static final String ERR_MSG_IND = "Round order index can't be null or smaller than 1 !";
    private static final String ERR_MSG_COMP = "Competition id can't be null !";

}
