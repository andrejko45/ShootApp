package com.danto.ShootApp.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateUserResponse(Long id, String name, String surname, String email, String phoneNumber) {



}
