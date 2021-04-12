package io.turq.turq.controller;

import com.stripe.exception.StripeException;
import io.turq.turq.exceptions.*;
import io.turq.turq.model.error.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<errorResponse> handleBadCredentials(BadCredentialsException e) {
        errorResponse error = new errorResponse("Login Failure", "Invalid username/password supplied");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ContestNotFoundException.class})
    public ResponseEntity<errorResponse> handleContestNotFound(ContestNotFoundException e) {
        errorResponse error = new errorResponse("Contest Not Found", e.getMsg());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {LegislationNotFoundException.class})
    public ResponseEntity<errorResponse> handleLegislationNotFound(LegislationNotFoundException e) {
        errorResponse error = new errorResponse("Legislation Not Found", e.getMsg());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserBadRequestException.class})
    public ResponseEntity<errorResponse> handleUserBadRequest(UserBadRequestException e) {
        errorResponse error = new errorResponse("Bad Request", e.getMsg());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<errorResponse> handleUserBadRequest(UserAlreadyExistsException e) {
        errorResponse error = new errorResponse("Email already used", e.getMsg());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<errorResponse> handleUserBadRequest(ForbiddenException e) {
        errorResponse error = new errorResponse("Unauthorized", e.getMsg());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {StripeException.class})
    public ResponseEntity<errorResponse> handleStripeException(StripeException e) {
        errorResponse error = new errorResponse("We were unable to process your payment at this time", null);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
