package com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDigitallPayment extends RuntimeException {

    private HttpStatus httpStatus;

    public ExceptionDigitallPayment(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
