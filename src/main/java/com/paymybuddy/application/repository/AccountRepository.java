package com.paymybuddy.application.repository;

import com.paymybuddy.application.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    /**
     * Trouve un compte avec l'iban
     * @param iban
     * @return
     */
    Optional<Account> findAllByIban(String iban);
}
