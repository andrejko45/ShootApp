package com.danto.ShootApp.dto.competition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record UpdateCompetitionRequest(@NotNull(message = ERR_ID) Long id, @NotBlank(message = ERR_NAME) String name,@NotBlank(message = ERR_PLACE) String place, @NotNull(message = ERR_DATE) LocalDate date) {

    private static final String ERR_ID = "Id can't be null !";
    private static final String ERR_NAME = "Name can't be empty !";
    private static final String ERR_PLACE = "Place can't be empty";
    private static final String ERR_DATE = "Date can't be null !";
}
