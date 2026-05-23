package com.danto.ShootApp.dto.competition;

import java.time.LocalDate;

public record UpdateCompetitionRequest(Long id, String name, String place, LocalDate date) {
}
