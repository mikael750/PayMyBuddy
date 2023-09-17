package com.paymybuddy.application.repository;

import com.paymybuddy.application.models.Account;
import com.paymybuddy.application.models.User;
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
    Optional<Account> findByIban(String iban);

    /**
     * @param id
     * @return
     */
    Optional<Account> findById(Integer id);

    /**
     * @param user
     * @return
     */
    Optional<Account> findByUser(User user);
}
