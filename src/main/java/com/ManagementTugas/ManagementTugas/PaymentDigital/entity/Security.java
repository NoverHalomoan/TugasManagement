package com.ManagementTugas.ManagementTugas.PaymentDigital.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "datatask", name = "security")
public class Security {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Size(max = 100)
    private String id;

    @NotBlank
    @Column(name = "flogin_attemps")
    private Integer floginAttemps;

    @NotBlank
    @Column(name = "account_locked")
    private Boolean accountLocked;

    private Timestamp lastlogin;

    @OneToOne
    @JoinColumn(name = "username", unique = true, nullable = false)
    private Accounts accounts;
}
