package com.luanafaria.demoaccount.service;

import com.luanafaria.demoaccount.entity.Account;
import com.luanafaria.demoaccount.exception.ResourceNotFoundException;
import com.luanafaria.demoaccount.payload.AccountDto;
import com.luanafaria.demoaccount.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    private ModelMapper modelMapper;

    public Account createAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        return accountRepository.save(account);
    }

    public Account getAccountById(long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {
            return account.get();

        } else {
            throw new ResourceNotFoundException("Account", "AccountId", account.get().getId());
        }
    }
}
