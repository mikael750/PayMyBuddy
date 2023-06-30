package com.paymybuddy.application.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransferDTO {
    @NotEmpty
    String contactEmail;

    @NotNull
    @DecimalMin(value = "0.01", message = "Le montant ne peut pas etre inferieure a 0")
    BigDecimal amount = BigDecimal.ZERO;

    String transactionType;

    /**
     * Retourne le montant avec la taxe de 5/100
     * @return amount with fee
     */
    public BigDecimal getAmountWithFee(){
        final BigDecimal fee = amount.multiply(BigDecimal.valueOf(0.005));
        return amount.add(fee);
    }
}
