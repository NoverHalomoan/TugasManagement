package com.ManagementTugas.ManagementTugas.PaymentDigital.service;

import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ResponseWebDigitalPaymnet;
import com.ManagementTugas.ManagementTugas.PaymentDigital.request.DTOAccountRequest;

public interface BaseSrvice {

    ResponseWebDigitalPaymnet<?> createBased(Object request);

    ResponseWebDigitalPaymnet<?> updateBased(DTOAccountRequest request);

}
