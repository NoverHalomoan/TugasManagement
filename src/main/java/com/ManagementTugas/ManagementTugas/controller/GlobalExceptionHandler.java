package com.ManagementTugas.ManagementTugas.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @SuppressWarnings("rawtypes")
    private static ApiResponseData apiResponseData = new ApiResponseData<>();

    @SuppressWarnings("unchecked")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        apiResponseData.setData(errors);
        apiResponseData.setStatus(Integer.toString(HttpStatus.BAD_REQUEST.value()));
        apiResponseData.setMessage("Error Request Tidak Sesuai");
        return ResponseEntity.badRequest().body(apiResponseData);
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
        apiResponseData.setData(null);
        apiResponseData.setStatus(Integer.toString(HttpStatus.BAD_REQUEST.value()));
        apiResponseData.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(apiResponseData);
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> Exceptionhandle(ResponseStatusException ex) {
        apiResponseData.setData(null);
        apiResponseData.setStatus(Integer.toString(ex.getStatusCode().value()));
        apiResponseData.setMessage(ex.getReason());
        return ResponseEntity.badRequest().body(apiResponseData);
    }

}
