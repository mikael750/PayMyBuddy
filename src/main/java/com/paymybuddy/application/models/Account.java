package com.paymybuddy.application.models;

import com.paymybuddy.application.DTO.AccountDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String iban;

    @Column(nullable = false, unique = true)
    private String bic;

    @Column
    private BigDecimal amount;

    public Account(AccountDTO accountDTO){
        this.iban = accountDTO.getIban();
        this.bic = accountDTO.getBic();
        this.amount = accountDTO.getAmount();
    }

    @ManyToMany(
            cascade = CascadeType.PERSIST
    )
    private List<Account> accounts = new ArrayList<>();

    /**
     * Ajout un compte a l'utilisateur
     * @param account
     * @return
     */
    public Account addAccount(Account account){
        if (accounts.contains(account)) {
            throw new RuntimeException(account.getIban() + " is already registered");
        }
        accounts.add(account);
        return this;
    }
}
