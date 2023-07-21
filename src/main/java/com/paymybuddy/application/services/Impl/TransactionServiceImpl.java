package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.DTO.TransactionDTO;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.repository.TransactionRepository;
import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public List<TransactionDTO> findTransactionByEmail(String email) {
        User connectedUser = userRepository.findByEmail(email)
                .orElseThrow(()->  new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email));

        return transactionRepository.findAllBySenderOrReceiverOrderByDateDesc(connectedUser, connectedUser)
                .stream().map(TransactionDTO::new)
                .toList();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Page<TransactionDTO> getPage(Pageable pageable, String email) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TransactionDTO> list;

        List<TransactionDTO> transactionDTOList = findTransactionByEmail(email);
        if (transactionDTOList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, transactionDTOList.size());
            list = transactionDTOList.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), transactionDTOList.size());
    }

}
