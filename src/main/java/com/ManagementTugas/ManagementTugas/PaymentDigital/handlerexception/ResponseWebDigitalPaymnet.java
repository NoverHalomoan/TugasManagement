package com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseWebDigitalPaymnet<T> {

    private T data;

    private String Error;

}
