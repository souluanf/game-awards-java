package dev.luanfernandes.gameawards.api.handler;


import dev.luanfernandes.gameawards.domain.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.rmi.ServerException;
import java.time.Instant;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(HttpServletRequest req, EntityNotFoundException e) {
        return buildResponseEntity(new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                e.getMessage(),
                req.getRequestURI()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> validationException(HttpServletRequest req, ValidationException e) {
        return buildResponseEntity(new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Bad request", e.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<StandardError> serverException(HttpServletRequest req, ServerException e) {
        return buildResponseEntity(new StandardError(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> nullPointerException(HttpServletRequest req, NullPointerException e) {
        return buildResponseEntity(new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Resource Null Pointer", e.getMessage(), req.getRequestURI()));
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<StandardError> handleDataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest req) {
        if (e.getCause() == null) {
            return buildResponseEntity(new StandardError(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Unexpected error", e.getMessage(), req.getRequestURI()));
        }
        return buildResponseEntity(new StandardError(Instant.now(), HttpStatus.CONFLICT.value(),
                "Database error", e.getMessage(), req.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error(e.getMessage());
        return buildResponseEntity(new StandardError(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error", e.getMessage(), req.getRequestURI()));
    }

    private ResponseEntity<StandardError> buildResponseEntity(StandardError error) {
        log.error("{}",error);
        return ResponseEntity.status(HttpStatus.valueOf(error.getStatus())).body(error);
    }
}