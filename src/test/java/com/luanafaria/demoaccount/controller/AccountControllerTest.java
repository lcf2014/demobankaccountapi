package com.luanafaria.demoaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanafaria.demoaccount.DemoBankAccountApiApplication;
import com.luanafaria.demoaccount.payload.AccountDto;
import com.luanafaria.demoaccount.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoBankAccountApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void createAccount() throws Exception {
        Integer documentNumber = 123456789;
        AccountDto accountDto = new AccountDto(documentNumber);

        this.mockMvc
                .perform(post("/accounts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.documentNumber").value(documentNumber))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();
    }


}