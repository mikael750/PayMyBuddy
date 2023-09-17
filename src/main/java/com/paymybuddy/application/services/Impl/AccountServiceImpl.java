package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.models.Account;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> findAccountByIban(String iban) {
        return accountRepository.findByIban(iban);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Account saveAccount(AccountDTO accountDto) {
        Optional<Account> existingAccount = findAccountByIban(accountDto.getIban());
        if (existingAccount.isPresent()){
            throw new RuntimeException("The account of = " + accountDto.getBic() + " already in use !");
        }
        Account userAccount = new Account(accountDto);
        return accountRepository.save(userAccount);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<Account> getBankAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Optional<Account> getBankAccountById(Integer id) {
        Assert.notNull(id, "User ID must not be null");
        return accountRepository.findById(id);
    }
}
