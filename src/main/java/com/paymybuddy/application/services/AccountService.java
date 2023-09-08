package com.paymybuddy.application.services;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    /**
     * @param iban
     * @return
     */
    Optional<Account> findAccountByIban(String iban);

    /**
     * @param accountDto
     * @return
     */
    Account saveAccount(AccountDTO accountDto);

}
