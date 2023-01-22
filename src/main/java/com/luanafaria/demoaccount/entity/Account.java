package com.luanafaria.demoaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "accounts")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer documentNumber;

    public Account(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }
}
