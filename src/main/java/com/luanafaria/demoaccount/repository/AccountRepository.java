package com.luanafaria.demoaccount.repository;


import com.luanafaria.demoaccount.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
