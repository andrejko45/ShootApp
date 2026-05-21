package com.danto.ShootApp.dto.user;

import java.util.List;

public record UserErrorResponse(String message, List<String> errors) {
}
