package com.ManagementTugas.ManagementTugas.PaymentDigital.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Accounts;
import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Verification;
import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ExceptionDigitallPayment;
import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ResponseWebDigitalPaymnet;
import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ValidationRequestPayment;
import com.ManagementTugas.ManagementTugas.PaymentDigital.repository.AccountsRepository;
import com.ManagementTugas.ManagementTugas.PaymentDigital.request.DTOAccountRequest;
import com.ManagementTugas.ManagementTugas.PaymentDigital.response.DTOAccountResponse;
import com.ManagementTugas.ManagementTugas.base.abstracts.allabstracts;
import com.ManagementTugas.ManagementTugas.base.repository.GeneralRepository;
import com.ManagementTugas.ManagementTugas.security.BCrypt;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService extends allabstracts implements BaseSrvice {

    @Autowired
    private AccountsRepository accountRepository;

    @Autowired
    private ValidationRequestPayment validate;

    private ObjectMapper objectMapper;

    public AccountService(GeneralRepository generalRepository, ObjectMapper objectMapper) {
        super(generalRepository);
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseWebDigitalPaymnet<?> createBased(Object request) {
        try {
            String dtorequest = objectMapper.writeValueAsString(request);
            DTOAccountRequest realrequest = objectMapper.readValue(dtorequest, DTOAccountRequest.class);
            isExistAccount(realrequest.email());
            Accounts accounts = Accounts.toAccounts(realrequest);
            isSaveAccount(accounts);
            return ResponseWebDigitalPaymnet.<DTOAccountResponse>builder().data(isResponseAccount(accounts, null))
                    .build();
        } catch (Exception e) {
            throw new ExceptionDigitallPayment(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseWebDigitalPaymnet<?> updateBased(DTOAccountRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Accounts> getAllAccount() {
        return accountRepository.findAll();
    }

    public DTOAccountResponse createAccount(DTOAccountRequest request) {

        validate.ValidationRequest(request);
        Accounts accounts = new Accounts();
        accounts.setName(request.name());
        accounts.setUsername(request.username());
        accounts.setEmail(request.email());
        accounts.setPassword(BCrypt.hashpw(request.password(), BCrypt.gensalt()));
        accounts.setPhonenumber(request.phonenumber());
        accounts.setIsverified(false);

        accountRepository.save(accounts);

        return DTOAccountResponse.builder().build();

    }

}
