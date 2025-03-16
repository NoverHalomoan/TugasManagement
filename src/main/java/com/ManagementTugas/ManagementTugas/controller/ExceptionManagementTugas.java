package com.ManagementTugas.ManagementTugas.controller;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ExceptionManagementTugas extends RuntimeException {

    private HttpStatus httpStatus;

    public ExceptionManagementTugas(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    
}
