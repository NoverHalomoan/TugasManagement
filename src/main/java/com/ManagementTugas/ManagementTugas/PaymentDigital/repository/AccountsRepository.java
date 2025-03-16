package com.ManagementTugas.ManagementTugas.PaymentDigital.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Accounts;
import com.ManagementTugas.ManagementTugas.base.repository.BaseRepository;

@Repository
public interface AccountsRepository extends BaseRepository<Accounts, String> {

    Optional<Accounts> findByEmail(String email);
}
