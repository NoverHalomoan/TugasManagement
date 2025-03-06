package com.ManagementTugas.ManagementTugas.PaymentDigital.cntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ResponseWebDigitalPaymnet;
import com.ManagementTugas.ManagementTugas.PaymentDigital.request.DTOAccountRequest;
import com.ManagementTugas.ManagementTugas.PaymentDigital.response.DTOAccountResponse;
import com.ManagementTugas.ManagementTugas.PaymentDigital.service.AccountService;

@RestController
@RequestMapping("/api/payment")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseWebDigitalPaymnet<DTOAccountResponse> createAccount(
            @RequestBody DTOAccountRequest dtoAccountRequest) {

        DTOAccountResponse response = accountService.createAccount(dtoAccountRequest);
        return ResponseWebDigitalPaymnet.<DTOAccountResponse>builder().data(response).build();
    }

}
