package com.danto.ShootApp.controller;


import com.danto.ShootApp.customExceptions.UserNotFoundException;
import com.danto.ShootApp.dto.user.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public UserErrorResponse errorResponse(MethodArgumentNotValidException e) {

        List<String> userErrorsList = new ArrayList<>();
        List<FieldError> userFieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError userError :  userFieldErrors) {
            userErrorsList.add(userError.getDefaultMessage());
        }

        return new UserErrorResponse("Validation failed !", userErrorsList);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public UserErrorResponse notFoundResponse(UserNotFoundException e) {
        List<String> userNotFound = new ArrayList<>();
        userNotFound.add(e.getMessage());


        return new UserErrorResponse("Not found !", userNotFound);

    }

}
