package com.paymybuddy.application.models;

import com.paymybuddy.application.DTO.AccountDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String iban;

    private String bic;

    private BigDecimal amount;

    public Account(AccountDTO accountDTO){
        this.iban = accountDTO.getIban();
        this.bic = accountDTO.getBic();
        this.amount = accountDTO.getAmount();
    }
}
