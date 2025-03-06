package com.ManagementTugas.ManagementTugas.PaymentDigital.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "datatask", name = "verification")
public class Verification {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @NotBlank
    @Size(max = 10)
    private String otpcode;

    private Timestamp otpexpiration;

    @NotBlank
    @Size(max = 100)
    private String tokencode;

    private Timestamp tokenexpiration;

    @OneToOne()
    @JoinColumn(name = "username", unique = true, nullable = false)
    private Accounts accounts;

}
