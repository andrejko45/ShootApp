package com.danto.ShootApp.exceptions;


import com.danto.ShootApp.dto.competition.CompetitionNotFoundResponse;
import com.danto.ShootApp.dto.competition.DateNotValidResponse;
import com.danto.ShootApp.dto.user.UserNotFoundResponse;
import com.danto.ShootApp.exceptions.competitionExceptions.CompetitionNotFoundException;
import com.danto.ShootApp.exceptions.competitionExceptions.DateNotValidException;
import com.danto.ShootApp.exceptions.userExceptions.UserNotFoundException;
import com.danto.ShootApp.dto.user.UserValidationErrorResponse;
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

    // USER ERRORS AND EXCEPTIONS

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public UserValidationErrorResponse errorResponse(MethodArgumentNotValidException e) {

        List<String> userErrorsList = new ArrayList<>();
        List<FieldError> userFieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError userError :  userFieldErrors) {
            userErrorsList.add(userError.getDefaultMessage());
        }

        return new UserValidationErrorResponse("Validation failed !", userErrorsList);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public UserNotFoundResponse userNotFoundResponse(UserNotFoundException e) {
        return new UserNotFoundResponse(e.getMessage());
    }

    // COMPETITION ERRORS AND EXCEPTIONS

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateNotValidException.class)
    public DateNotValidResponse dateNotValidResponse(DateNotValidException e) {
        return new DateNotValidResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompetitionNotFoundException.class)
    public CompetitionNotFoundResponse competitionNotFoundResponse(CompetitionNotFoundException e) {
        return new CompetitionNotFoundResponse(e.getMessage());
    }


}
