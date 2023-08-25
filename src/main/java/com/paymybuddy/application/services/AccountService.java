package com.paymybuddy.application.services;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.models.Account;

import java.util.List;

public interface AccountService {

    /**
     * @param iban
     * @return
     */
    List<AccountDTO> findAccountList(String iban);

    /**
     * @param accountDto
     * @param iban
     * @return
     */
    Account addAccount(AccountDTO accountDto, String iban);
}
