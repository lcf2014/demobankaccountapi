package com.luanafaria.demoaccount.service;

import com.luanafaria.demoaccount.entity.Account;
import com.luanafaria.demoaccount.payload.AccountDto;
import com.luanafaria.demoaccount.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AccountService.class)
class AccountServiceTest {

    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Disabled
    @Test
   void createAccount() {
        Integer expectedDocumentNumber = 123456789;
        Account account = new Account(123456789);
        AccountDto accountDto = new AccountDto(123456789);

        when(modelMapper.map(accountDto, Account.class)).thenReturn(account);
       // when(accountRepository.save(any()))
       //         .thenReturn(new Account(Long.valueOf(123), expectedDocumentNumber));

        Account actualAccount = accountService.createAccount(accountDto);
        Assertions.assertEquals(expectedDocumentNumber, actualAccount.getDocumentNumber());
    }

    @Disabled
    @Test
    void getAccountById() {
        Integer expectedDocumentNumber = 123456789;
        Long id = Long.valueOf(123);
   //     Account expectedAccount = new Account(id, 123456789);
       // when(accountRepository.findById(id)).thenReturn(Optional.of(expectedAccount));
        Account actualAccount = accountService.getAccountById(id);
    //    Assertions.assertEquals(expectedAccount.getId(), actualAccount.getId());
    }

}