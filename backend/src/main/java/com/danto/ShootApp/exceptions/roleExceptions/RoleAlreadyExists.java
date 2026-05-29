package com.danto.ShootApp.exceptions.roleExceptions;

public class RoleAlreadyExists extends RuntimeException {
    public RoleAlreadyExists(String message) {
        super(message);
    }
}
