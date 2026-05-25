package com.danto.ShootApp.dto.competition;

import java.time.LocalDate;

// ADDING VALIDATION
public record UpdateCompetitionRequest(Long id, String name, String place, LocalDate date) {
}
