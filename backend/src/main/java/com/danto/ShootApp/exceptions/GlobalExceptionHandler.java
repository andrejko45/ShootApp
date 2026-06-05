package com.danto.ShootApp.exceptions;


import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.ValidationErrorResponse;
import com.danto.ShootApp.dto.competition.CompetitionNotFoundOrExistsResponse;
import com.danto.ShootApp.dto.competition.DateNotValidResponse;
import com.danto.ShootApp.dto.user.UserNotFoundOrExistsResponse;
import com.danto.ShootApp.exceptions.competitionExceptions.CompAlreadyExists;
import com.danto.ShootApp.exceptions.competitionExceptions.CompHasParticipation;
import com.danto.ShootApp.exceptions.competitionExceptions.CompetitionNotFoundException;
import com.danto.ShootApp.exceptions.competitionExceptions.DateNotValidException;
import com.danto.ShootApp.exceptions.roleExceptions.RoleAlreadyExists;
import com.danto.ShootApp.exceptions.roleExceptions.RoleHasParticipation;
import com.danto.ShootApp.exceptions.roleExceptions.RoleNotFoundException;
import com.danto.ShootApp.exceptions.userExceptions.UserAlreadyExists;
import com.danto.ShootApp.exceptions.userExceptions.UserHasParticipationException;
import com.danto.ShootApp.exceptions.userExceptions.UserNotFoundException;
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
    public ValidationErrorResponse validationErrorResponse(MethodArgumentNotValidException e) {

        List<String> validErrorsList = new ArrayList<>();
        List<FieldError> validFieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError validError :  validFieldErrors) {
            validErrorsList.add(validError.getDefaultMessage());
        }

        return new ValidationErrorResponse("Validation failed !", validErrorsList);
    }



    // USER ERRORS AND EXCEPTIONS

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public UserNotFoundOrExistsResponse userNotFoundResponse(UserNotFoundException e) {
        return new UserNotFoundOrExistsResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExists.class)
    public UserNotFoundOrExistsResponse userAlreadyExists(UserAlreadyExists e) {
        return new UserNotFoundOrExistsResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserHasParticipationException.class)
    public DeleteResponse userHasParticipations(UserHasParticipationException e) {
        return new DeleteResponse(e.getMessage());
    }


    // COMPETITION ERRORS AND EXCEPTIONS

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateNotValidException.class)
    public DateNotValidResponse dateNotValidResponse(DateNotValidException e) {
        return new DateNotValidResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompetitionNotFoundException.class)
    public CompetitionNotFoundOrExistsResponse competitionNotFoundResponse(CompetitionNotFoundException e) {
        return new CompetitionNotFoundOrExistsResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompAlreadyExists.class)
    public CompetitionNotFoundOrExistsResponse competitionAlreadyExistsResponse(CompAlreadyExists e) {
        return new CompetitionNotFoundOrExistsResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompHasParticipation.class)
    public DeleteResponse compHasParticipation(CompHasParticipation e) {
        return new DeleteResponse(e.getMessage());
    }

    // ROLES ERRORS AND EXCEPTIONS
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RoleAlreadyExists.class)
    public RoleAlreadyExists roleAlreadyExistsResponse(RoleAlreadyExists e) {
        return new RoleAlreadyExists(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public RoleNotFoundException roleNotFoundResponse(RoleNotFoundException e) {
        return new RoleNotFoundException(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RoleHasParticipation.class)
    public DeleteResponse roleHasParticipation(RoleHasParticipation e) {
        return new DeleteResponse(e.getMessage());
    }


}
