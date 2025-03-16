package com.ManagementTugas.ManagementTugas.base.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ManagementTugas.ManagementTugas.PaymentDigital.repository.AccountsRepository;
import com.ManagementTugas.ManagementTugas.PaymentDigital.repository.SecurityRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GeneralRepository {
    public final AccountsRepository accountRepository;

    public final SecurityRepository securityRepository;
}
