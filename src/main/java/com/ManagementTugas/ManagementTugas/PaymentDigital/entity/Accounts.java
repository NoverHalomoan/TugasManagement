package com.ManagementTugas.ManagementTugas.PaymentDigital.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "datatask", name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @NotBlank
    @Size(max = 100)
    @Id
    private String username;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size(max = 15)
    private String phonenumber;

    @NotBlank
    @Size(max = 100)
    private String password;

    @Column(nullable = false)
    private Boolean isverified = false;

    private Timestamp updateat;

    @OneToOne(mappedBy = "accounts", cascade = CascadeType.ALL)
    private Verification verification;

    @OneToOne(mappedBy = "accounts", cascade = CascadeType.ALL)
    private Security security;

    @PreUpdate
    protected void onUpdateat() {
        updateat = Timestamp.valueOf(LocalDateTime.now());
    }

}
