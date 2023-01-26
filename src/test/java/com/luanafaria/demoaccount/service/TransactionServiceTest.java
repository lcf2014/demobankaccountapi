package com.luanafaria.demoaccount.service;

import com.luanafaria.demoaccount.entity.Transaction;
import com.luanafaria.demoaccount.payload.TransactionDto;
import com.luanafaria.demoaccount.repository.AccountRepository;
import com.luanafaria.demoaccount.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TransactionService.class)
class TransactionServiceTest {

    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;


}