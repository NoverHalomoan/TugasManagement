package com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class ValidationRequestPayment {

    @Autowired
    private Validator validator;

    public void ValidationRequest(Object requObject) {
        Set<ConstraintViolation<Object>> constrainviol = validator.validate(requObject);

        if (constrainviol.size() != 0) {
            String message = constrainviol.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
            throw new ExceptionDigitallPayment(message, HttpStatus.BAD_REQUEST);
        }
    }

}
