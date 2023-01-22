package com.luanafaria.demoaccount.controller;

import com.luanafaria.demoaccount.entity.Account;
import com.luanafaria.demoaccount.payload.AccountDto;
import com.luanafaria.demoaccount.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping()
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // get post by id
    @GetMapping(value = "/accounts/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable(name = "accountId") long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

}
