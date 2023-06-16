package com.paymybuddy.application.repository;

import com.paymybuddy.application.models.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {
    Optional<UserAccount> findByEmail(String email);
}
