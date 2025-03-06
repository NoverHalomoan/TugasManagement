package com.ManagementTugas.ManagementTugas.PaymentDigital.request;

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

}
