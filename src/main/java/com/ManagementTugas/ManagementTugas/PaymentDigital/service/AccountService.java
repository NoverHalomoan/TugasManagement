package com.ManagementTugas.ManagementTugas.PaymentDigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Accounts;
import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Verification;
import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ValidationRequestPayment;
import com.ManagementTugas.ManagementTugas.PaymentDigital.repository.AccountsRepository;
import com.ManagementTugas.ManagementTugas.PaymentDigital.request.DTOAccountRequest;
import com.ManagementTugas.ManagementTugas.PaymentDigital.response.DTOAccountResponse;
import com.ManagementTugas.ManagementTugas.security.BCrypt;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountsRepository accountRepository;

    @Autowired
    private ValidationRequestPayment validate;

    public DTOAccountResponse createAccount(DTOAccountRequest request) {

        validate.ValidationRequest(request);
        log.info("Masuk Service");
        Accounts accounts = new Accounts();
        accounts.setName(request.name());
        accounts.setUsername(request.username());
        accounts.setEmail(request.email());
        accounts.setPassword(BCrypt.hashpw(request.password(), BCrypt.gensalt()));
        accounts.setPhonenumber(request.phonenumber());
        accounts.setIsverified(false);

        accountRepository.save(accounts);

        return toDtoAccountResponse(null, null);

    }

    private DTOAccountResponse toDtoAccountResponse(Accounts accounts, Verification verification) {
        return DTOAccountResponse.builder().build();
    }

}
