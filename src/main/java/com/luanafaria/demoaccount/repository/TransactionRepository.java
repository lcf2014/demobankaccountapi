package com.luanafaria.demoaccount.repository;

import com.luanafaria.demoaccount.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
