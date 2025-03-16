package com.ManagementTugas.ManagementTugas.PaymentDigital.request;

import com.ManagementTugas.ManagementTugas.PaymentDigital.entity.Accounts;
import com.ManagementTugas.ManagementTugas.PaymentDigital.response.DTOAccountResponse;
import com.ManagementTugas.ManagementTugas.security.BCrypt;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record DTOAccountRequest(@NotBlank @Size(max = 100) String username,
                @NotBlank @Size(max = 100) String name,
                @Email @Size(max = 100) String email,
                @Size(max = 100) String password,
                @Size(max = 15) String phonenumber) {

        public Accounts toAccount() {
                Accounts accounts = new Accounts();
                accounts.setName(this.name());
                accounts.setUsername(this.username());
                accounts.setEmail(this.email());
                accounts.setPassword(BCrypt.hashpw(this.password(), BCrypt.gensalt()));
                accounts.setPhonenumber(this.phonenumber());
                accounts.setIsverified(false);
                return accounts;
        }

}
