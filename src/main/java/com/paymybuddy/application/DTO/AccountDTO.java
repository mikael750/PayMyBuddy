package com.paymybuddy.application.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @NotNull
    String iban;

    @NotNull
    String bic;

    @NotNull
    @Digits(integer = 4, fraction = 2)
    BigDecimal amount;
}
