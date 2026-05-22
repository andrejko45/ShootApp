package com.danto.ShootApp.dto.user;

import java.util.List;

public record UserValidationErrorResponse(String message, List<String> errors) {
}
