package com.danto.ShootApp.exceptions.competitionExceptions;

public class CompAlreadyExists extends RuntimeException {
    public CompAlreadyExists(String message) {
        super(message);
    }
}
