package com.luanafaria.demoaccount.service;

import com.luanafaria.demoaccount.entity.Account;
import com.luanafaria.demoaccount.entity.Transaction;
import com.luanafaria.demoaccount.exception.BadRequestException;
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
import java.util.Optional;

import static com.luanafaria.demoaccount.enums.OperationTypeId.WITHDRAW;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TransactionService.class)
class TransactionServiceTest {

    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    void createTransaction() {
        Transaction transaction = new Transaction(123456L, 123456L, WITHDRAW,
                BigDecimal.valueOf(-0.5), LocalDateTime.now());
        TransactionDto transactionDto = new TransactionDto(123456L, WITHDRAW, BigDecimal.valueOf(0.5));
        when(accountRepository.findById(any())).thenReturn(Optional.of(
                new Account(123456L, 123456789, BigDecimal.valueOf(5000))));

        when(modelMapper.map(transactionDto, Transaction.class)).thenReturn(transaction);
        when(transactionRepository.save(any())).thenReturn(transaction);

        Transaction actualTransaction = transactionService.createTransaction(transactionDto);
        Assertions.assertEquals(BigDecimal.valueOf(-0.5), actualTransaction.getAmount());
    }

    @Test
    void createTransactionShouldThrowBadRequestException() {
        Transaction transaction = new Transaction(123456L, 123456L, WITHDRAW,
                BigDecimal.valueOf(-0.5), LocalDateTime.now());
        TransactionDto transactionDto = new TransactionDto(123456L, WITHDRAW, BigDecimal.valueOf(5500));
        when(accountRepository.findById(any())).thenReturn(Optional.of(
                new Account(123456L, 123456789, BigDecimal.valueOf(5000))));

        when(modelMapper.map(transactionDto, Transaction.class)).thenReturn(transaction);
        when(transactionRepository.save(any())).thenReturn(transaction);

        assertThatThrownBy(() -> transactionService.createTransaction(transactionDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Bad request with AvailableLimitCredit : '5000'");
    }

}