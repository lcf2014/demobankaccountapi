package com.luanafaria.demoaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanafaria.demoaccount.DemoBankAccountApiApplication;
import com.luanafaria.demoaccount.entity.Account;
import com.luanafaria.demoaccount.payload.TransactionDto;
import com.luanafaria.demoaccount.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoBankAccountApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void createTransaction() throws Exception {
        Account account = new Account(Long.valueOf(123), 123456789);
        accountRepository.save(account);

        TransactionDto transaction = new TransactionDto(Long.valueOf(123), 1, BigDecimal.valueOf(6.0));

        this.mockMvc
                .perform(post("/transactions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId").value(123))
                .andExpect(jsonPath("$.operationTypeId").value(1))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();
    }
}