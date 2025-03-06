package com.ManagementTugas.ManagementTugas.PaymentDigital.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record DTOAccountResponse(
        String username,
        String name,
        String email,
        String password,
        String phonenumber,
        String tokencode,
        Boolean isverified) {

}
