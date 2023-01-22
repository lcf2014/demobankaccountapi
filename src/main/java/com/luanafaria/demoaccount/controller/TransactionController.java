package com.luanafaria.demoaccount.controller;

import com.luanafaria.demoaccount.entity.Transaction;
import com.luanafaria.demoaccount.payload.TransactionDto;
import com.luanafaria.demoaccount.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping()
@AllArgsConstructor
public class TransactionController {
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<Transaction>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }
}
