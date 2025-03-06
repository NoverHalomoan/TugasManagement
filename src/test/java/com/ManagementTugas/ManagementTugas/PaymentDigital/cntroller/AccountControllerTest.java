package com.ManagementTugas.ManagementTugas.PaymentDigital.cntroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ResponseWebDigitalPaymnet;
import com.ManagementTugas.ManagementTugas.PaymentDigital.repository.AccountsRepository;
import com.ManagementTugas.ManagementTugas.PaymentDigital.request.DTOAccountRequest;
import com.ManagementTugas.ManagementTugas.PaymentDigital.response.DTOAccountResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    private String urlpattern = "/api/payment";

    @Autowired
    private AccountsRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        if (accountRepository.findAll().size() != 0) {
            accountRepository.deleteAll();
        }
    }

    @Test
    void testCreateAccountBadRequest() throws Exception {
        DTOAccountRequest dtoAccountRequest = DTOAccountRequest.builder().name("Test").email("Test")
                .phonenumber("08129992839").password("Kosong").username("Test").build();

        mockMvc.perform(
                post(urlpattern + "/account").accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dtoAccountRequest)))
                .andExpectAll(status().isBadRequest())
                .andDo(
                        results -> {
                            ResponseWebDigitalPaymnet<String> response = objectMapper
                                    .readValue(results.getResponse().getContentAsString(), new TypeReference<>() {
                                    });
                            assertNotNull(response.getError());
                        });
    }

    @Test
    void testCreateAccountSuccess() throws Exception {
        DTOAccountRequest dtoAccountRequest = DTOAccountRequest.builder().name("Test").email("Test@gmail.com")
                .phonenumber("08129992839").password("Kosong").username("Test").build();

        mockMvc.perform(
                post(urlpattern + "/account").accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dtoAccountRequest)))
                .andExpectAll(status().isOk())
                .andDo(
                        results -> {
                            ResponseWebDigitalPaymnet<DTOAccountResponse> response = objectMapper
                                    .readValue(results.getResponse().getContentAsString(), new TypeReference<>() {
                                    });
                            assertNull(response.getError());
                        });
    }
}
