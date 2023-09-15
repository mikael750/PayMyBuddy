package com.paymybuddy.application.DTO;

import com.paymybuddy.application.models.Transaction;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TransactionDTO {

    private BigDecimal amount;

    @Size(max = 500)
    private String transactionType;

    private String sender;

    private String receiver;

    private LocalDate date;

    public TransactionDTO(Transaction transaction){
        amount = transaction.getAmount();
        sender = transaction.getSender().getFullName();
        receiver = transaction.getReceiver().getFullName();
        transactionType = transaction.getTransactionType();
        date = transaction.getDate();
    }
}
