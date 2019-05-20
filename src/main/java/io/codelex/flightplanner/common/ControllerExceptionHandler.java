package io.codelex.flightplanner.common;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
class ControllerExceptionHandler {

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({IllegalStateException.class, DataIntegrityViolationException.class})
    public void handleIllegalState() {
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgument() {
    }
}
