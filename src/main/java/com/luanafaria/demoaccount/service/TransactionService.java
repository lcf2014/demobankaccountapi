package com.luanafaria.demoaccount.service;


import com.luanafaria.demoaccount.entity.Account;
import com.luanafaria.demoaccount.entity.Transaction;
import com.luanafaria.demoaccount.enums.OperationTypeId;
import com.luanafaria.demoaccount.exception.BadRequestException;
import com.luanafaria.demoaccount.exception.ResourceNotFoundException;
import com.luanafaria.demoaccount.payload.TransactionDto;
import com.luanafaria.demoaccount.repository.AccountRepository;
import com.luanafaria.demoaccount.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private ModelMapper modelMapper;

    private TransactionRepository transactionRepository;

    private AccountRepository accountRepository;

    @Transactional
    public Transaction createTransaction(TransactionDto transactionDto) {
        OperationTypeId operationTypeId = transactionDto.getOperationTypeId();

        if (operationTypeId.getValue() <= OperationTypeId.WITHDRAW.getValue()) {
            transactionDto.setAmount(transactionDto.getAmount().abs().negate());
        } else {
            transactionDto.setAmount(transactionDto.getAmount().abs());
        }

        Optional<Account> account = accountRepository.findById(transactionDto.getAccountId());

        updateAvailableCreditLimit(account, transactionDto);

        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        transaction.setEventDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    void updateAvailableCreditLimit(final Optional<Account> account, TransactionDto transactionDto){
        if (account.isPresent()) {
            BigDecimal availableCreditLimit = account.get().getAvailableCreditLimit();
            BigDecimal updatedavailableCreditLimit = availableCreditLimit.add(transactionDto.getAmount());
            if (updatedavailableCreditLimit.compareTo(BigDecimal.ZERO) == -1) {
                throw new BadRequestException("AvailableLimitCredit", availableCreditLimit.toString());
            }

            account.get().setAvailableCreditLimit(availableCreditLimit.add(transactionDto.getAmount()));
            accountRepository.save(account.get());

        } else {
            throw new ResourceNotFoundException("Account", "AccountId", transactionDto.getAccountId());
        }
    }
}
