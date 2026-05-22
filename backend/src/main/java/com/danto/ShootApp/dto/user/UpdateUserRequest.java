package com.danto.ShootApp.dto.user;

public record UpdateUserRequest(Long id, String name, String surname, String email, String phoneNumber) {}
