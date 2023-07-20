package com.paymybuddy.application.services;

import com.paymybuddy.application.DTO.TransactionDTO;

import java.util.List;

public interface TransactionService {

    /**
     * Trouve les transactions d'un utilisateur
     * @param email l'email de l'utilisateur
     * @return une liste de transaction
     */
    List<TransactionDTO> findTransactionByUser(String email);
}
