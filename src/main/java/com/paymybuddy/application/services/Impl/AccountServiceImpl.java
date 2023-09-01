package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.models.Account;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

        /**
         *{@inheritDoc}
         */
    @Override
    public List<AccountDTO> findAccountList(String iban) {
        Optional<Account> userGetAccount = accountRepository.findByIban(iban);
        if (userGetAccount.isEmpty()){
            throw new UsernameNotFoundException("Account not found with iban: " + iban);
        }
        return userGetAccount.get().getAccounts()
                .stream().map(AccountDTO::new)
                .toList();
    }

    /**
     *{@inheritDoc}
     * @return
     */
    @Override
    public Account addAccount(AccountDTO accountDto, String iban) {
        Account accountToAdd = accountRepository.findByIban(accountDto.getIban())
                .orElseThrow(() -> new UsernameNotFoundException("Account not found with iban : " + iban));

        Account accountToAddAccount = accountRepository.findByIban(iban)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found with iban : " + iban))
                .addAccount(accountToAdd);

        return accountRepository.save(accountToAddAccount);
    }
}
