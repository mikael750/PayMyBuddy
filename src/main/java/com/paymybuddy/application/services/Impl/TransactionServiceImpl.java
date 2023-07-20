package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.DTO.TransactionDTO;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.repository.TransactionRepository;
import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     *{@inheritDoc}
     */
    @Override
    public List<TransactionDTO> findTransactionByUser(String email) {
        User connectedUser = userRepository.findByEmail(email)
                .orElseThrow(()->  new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email));

        return transactionRepository.findAllBySenderOrReceiverOrderByDateDesc(connectedUser, connectedUser)
                .stream().map(TransactionDTO::new)
                .toList();
    }



}
