package com.paymybuddy.application.repository;

import com.paymybuddy.application.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * Trouve un utilisateur avec son email
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    Optional<User> findByFirstNameAndLastName(String firstname, String lastname);
}
