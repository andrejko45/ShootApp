package com.danto.ShootApp.exceptions.competitionExceptions;

public class CompetitionNotFoundException extends RuntimeException {
    public CompetitionNotFoundException(String message) {
        super(message);
    }
}
