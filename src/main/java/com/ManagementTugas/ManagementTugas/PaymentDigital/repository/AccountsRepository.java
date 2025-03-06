package com.ManagementTugas.ManagementTugas.PaymentDigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, String> {

}
