package com.paymybuddy.application.services;

import com.paymybuddy.application.DTO.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    /**
     * Trouve les transactions d'un utilisateur
     * @param email l'email de l'utilisateur
     * @return une liste de transaction
     */
    List<TransactionDTO> findTransactionByUser(String email);

    /**
     * obtient la pagination de la liste de transaction d'un utilisateur
     * @param pageable Savoir si la page est pageable
     * @param email l'email d'un utilisateur
     * @return la pagination de la liste de transaction
     */
    Page<TransactionDTO> getPage(Pageable pageable, String email);
}
