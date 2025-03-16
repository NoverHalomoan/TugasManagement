package com.ManagementTugas.ManagementTugas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.ConstraintViolationException;

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

    @ExceptionHandler(ExceptionManagementTugas.class)
    public ResponseEntity<?> ExceptionManagementTugas(ExceptionManagementTugas ex) {

        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> ExceptionSizeFile(MaxUploadSizeExceededException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(cv -> {
            String propertyPath = cv.getPropertyPath().toString();
            String field = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
            errors.put(field, cv.getMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String dbErrorMessage = Optional.ofNullable(ex.getCause())
                .map(Throwable::getCause)
                .map(Throwable::getMessage)
                .orElse("");
        String message = "Database error: Integrity constraint violation";
        List<Map<String, String>> errors = parseDataIntegrityViolation(dbErrorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private List<Map<String, String>> parseDataIntegrityViolation(String dbErrorMessage) {
        List<Map<String, String>> errors = new ArrayList<>();
        try {
            String detailPart = dbErrorMessage.split("Detail:")[1].trim();
            String fieldSub = detailPart.substring(detailPart.indexOf("(") + 1, detailPart.indexOf(")"));
            String valueSub = detailPart.substring(detailPart.indexOf(")=(") + 3, detailPart.lastIndexOf(")"));
            String finalMessage = Optional.of(detailPart)
                    .filter(s -> s.contains("already exists"))
                    .map(s -> "already exists")
                    .orElse("duplicate error");
            Map<String, String> fieldError = new LinkedHashMap<>();
            fieldError.put("field", fieldSub);
            fieldError.put("value", valueSub);
            fieldError.put("message", finalMessage);
            errors.add(fieldError);
        } catch (Exception e) {
            Map<String, String> fallbackError = new LinkedHashMap<>();
            fallbackError.put("field", "unknown");
            fallbackError.put("value", "unknown");
            fallbackError.put("message", dbErrorMessage);
            errors.add(fallbackError);
        }
        return errors;
    }

}
