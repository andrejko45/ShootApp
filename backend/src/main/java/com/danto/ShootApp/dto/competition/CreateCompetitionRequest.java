package com.danto.ShootApp.dto.competition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateCompetitionRequest(@NotBlank(message = ERR_COM_NAME) String name, @NotBlank(message = ERR_COM_PLACE) String place, @NotNull(message = ERR_COM_DATE)
                                       LocalDate date) {

    private static final String ERR_COM_NAME = "Name can't be empty !";
    private static final String ERR_COM_PLACE = "Place can't be empty !";
    private static final String ERR_COM_DATE = "Date can't be null !";

}
