package com.paymybuddy.application.services;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findAccountByIban(String iban);

    Account saveAccount(AccountDTO accountDto);

}
