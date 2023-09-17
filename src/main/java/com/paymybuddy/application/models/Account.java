package com.paymybuddy.application.models;

import com.paymybuddy.application.DTO.AccountDTO;
import jakarta.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = true)
    private String iban;

    @Column(nullable = false, unique = true)
    private String bic;

    @Column
    private BigDecimal amount;

    public Account(AccountDTO accountDTO){
        this.user = accountDTO.getUser();
        this.iban = accountDTO.getIban();
        this.bic = accountDTO.getBic();
        this.amount = accountDTO.getAmount();
    }
}
