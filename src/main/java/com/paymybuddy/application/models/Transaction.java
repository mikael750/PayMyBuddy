package com.paymybuddy.application.models;

import com.paymybuddy.application.DTO.MoneyTransferDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal originalAmount;

    private BigDecimal amount;

    private String transactionType;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private LocalDate date;

    public Transaction(MoneyTransferDTO moneyTransferDTO, User sender, User receiver){
        this.originalAmount = moneyTransferDTO.getAmount();
        this.amount = moneyTransferDTO.getAmountWithFee();
        this.transactionType = moneyTransferDTO.getTransactionType();
        this.sender = sender;
        this.receiver= receiver;
        this.date = LocalDate.now();
    }
}
