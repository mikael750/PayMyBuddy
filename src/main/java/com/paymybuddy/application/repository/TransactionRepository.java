package com.paymybuddy.application.repository;

import com.paymybuddy.application.models.Transaction;
import com.paymybuddy.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    /**
     * Trouve une liste de transaction d'un utilisateur
     * @param sender l'utilisateur pour trouver la transaction
     * @param receiver l'utilisateur pour trouver la transaction
     * @return une liste de transaction
     */
    List<Transaction> findAllBySenderOrReceiverOrderByDateDesc(User sender, User receiver);

    List<Transaction> findAllBySender(User sender);

    //List<Transaction> findAllByContacts(User contacts);
}
