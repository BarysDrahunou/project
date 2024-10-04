package com.senla.finance.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleEmailValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler({UserNotFoundException.class, CompanyNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
        return responseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleException(UserAlreadyExistsException e) {
        return responseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({IllegalArgumentException.class, PropertyNotValidException.class})
    public ResponseEntity<String> handleNotValidArgumentsException(RuntimeException e) {
        return responseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> responseEntity(String message, HttpStatusCode status) {
        return ResponseEntity
                .status(status)
                .body(message);
    }
}
