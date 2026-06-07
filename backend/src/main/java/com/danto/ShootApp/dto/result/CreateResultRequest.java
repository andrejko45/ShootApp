package com.danto.ShootApp.dto.result;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateResultRequest(Long partId, Long roundId, @NotNull(message = ERR_MSG_POINTS) @Min(value = 0, message = "Points can't be negative") Integer points) {

    private static final String ERR_MSG_POINTS = "Points can't be empty !";

}
