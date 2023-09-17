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

    /**
     * Lists all bank accounts in database
     *
     * @return a list of bank accounts
     */
    List<Account> getBankAccounts();

    /**
     * Returns a bank account given an ID.
     * @param id ID  bank account needed.
     * @return a bank account if exists, empty optional otherwise.
     */
    Optional<Account> getBankAccountById(Integer id);
}
