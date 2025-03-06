package com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DigitalGlobalHandler {

    @ExceptionHandler(ExceptionDigitallPayment.class)
    public ResponseEntity<ResponseWebDigitalPaymnet<String>> rExceptionDigitalPayment(
            ExceptionDigitallPayment exception) {

        return ResponseEntity.status(exception.getHttpStatus())
                .body(ResponseWebDigitalPaymnet.<String>builder().Error(exception.getMessage()).build());
    }
}
