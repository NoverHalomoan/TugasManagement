package com.ManagementTugas.ManagementTugas.base.abstracts;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Accounts;
import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Verification;
import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ExceptionDigitallPayment;
import com.ManagementTugas.ManagementTugas.PaymentDigital.response.DTOAccountResponse;
import com.ManagementTugas.ManagementTugas.base.repository.GeneralRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class allabstracts {

    private GeneralRepository generalRepository;

    public allabstracts(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
    }

    public void isExistAccount(String email) {

        generalRepository.accountRepository.findByEmail(email).ifPresent(accounts -> {
            throw new ExceptionDigitallPayment("Account Sudah Pernah Didaftarkan", HttpStatus.ALREADY_REPORTED);
        });

    }

    @Transactional
    public void isSaveAccount(Accounts accounts) {
        try {
            // Validate required fields
            if (accounts.getEmail() == null || accounts.getEmail().isBlank()) {
                throw new ExceptionDigitallPayment("Email cannot be null or empty", HttpStatus.BAD_REQUEST);
            }
            if (accounts.getPassword() == null || accounts.getPassword().isBlank()) {
                throw new ExceptionDigitallPayment("Password cannot be null or empty", HttpStatus.BAD_REQUEST);
            }

            generalRepository.accountRepository.save(accounts);
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionDigitallPayment("Database constraint violation: " + e.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            throw new ExceptionDigitallPayment("Transaction failed due to validation errors: " + e.getRootCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new ExceptionDigitallPayment("Unexpected error while saving account: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public DTOAccountResponse isResponseAccount(Accounts accounts, Verification verification) {
        return DTOAccountResponse.builder().name(accounts.getName()).email(accounts.getEmail()).name(accounts.getName())
                .build();
    }

}
