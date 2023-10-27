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
     * Trouve un compte avec l'id
     * @param id
     * @return
     */
    Optional<Account> findById(Integer id);

    /**
     * Trouve un compte avec l'utilisateur
     * @param user
     * @return
     */
    Optional<Account> findByUser(User user);
}
