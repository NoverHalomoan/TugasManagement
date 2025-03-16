package com.ManagementTugas.ManagementTugas.PaymentDigital.repository;

import org.springframework.stereotype.Repository;

import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Verification;
import com.ManagementTugas.ManagementTugas.base.repository.BaseRepository;

@Repository
public interface VerificationRepository extends BaseRepository<Verification, String> {

}
