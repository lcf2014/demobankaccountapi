package com.luanafaria.demoaccount.service;


import com.luanafaria.demoaccount.entity.Transaction;
import com.luanafaria.demoaccount.payload.TransactionDto;
import com.luanafaria.demoaccount.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransactionService {

    private ModelMapper modelMapper;

    private TransactionRepository transactionRepository;

    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        transaction.setEventDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}
