package com.paymybuddy.application.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransferDTO {
    @NotEmpty
    String contactEmail;

    @NotNull
    @DecimalMin(value = "0.01", message = "The Amount of money cannot be equal or less than 0")
    BigDecimal amount = BigDecimal.ZERO;

    public MoneyTransferDTO(String contact, BigDecimal valueOf) {
        this.contactEmail = contact;
        this.amount = valueOf;
    }

    String transactionType;

    /*
    enum transactionType{
        TRANSFER,
        DEPOSIT,
        RETRAIT
    }

    public transactionType[] getTransactionType() {
        return transactionType.values();
    }
*/
    @Value("${application.fee}")
    private long valueFee;

    /**
     * Retourne le montant avec la Commission de 5/100
     * @return amount with fee
     */
    public BigDecimal getAmountWithFee(){
        final BigDecimal fee = amount.multiply(BigDecimal.valueOf(valueFee));
        return amount.add(fee);
    }
}
