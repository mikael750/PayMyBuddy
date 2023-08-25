package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.DTO.MoneyTransferDTO;
import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.models.Transaction;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.repository.TransactionRepository;
import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     *{@inheritDoc}
     */
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public User saveUser(UserDTO userDto) {
        Optional<User> existingUser = findUserByEmail(userDto.getEmail());
        if (existingUser.isPresent()){
            throw new RuntimeException("The email = " + userDto.getEmail() + " is already in use !");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userAccount = new User(userDto, passwordEncoder);
        return userRepository.save(userAccount);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<ContactDTO> findContactList(String email) {
        Optional<User> userGetContact = userRepository.findByEmail(email);
        if (userGetContact.isEmpty()){
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return userGetContact.get().getContacts()
                .stream().map(ContactDTO::new)
                .toList();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public User addContact(ContactDTO contactDto, String email) {
        User contactToAdd = userRepository.findByEmail(contactDto.getEmail())
                      .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        User userAccountToAddContact = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email))
                .addContact(contactToAdd);

        return userRepository.save(userAccountToAddContact);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public BigDecimal getBalance(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email))
                .getSolde();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public User bankTransfer(AccountDTO accountDto, String email) {
        final User userAccount = findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email))
                .creditBalance(accountDto.getAmount());
        return userRepository.save(userAccount);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Transaction transferMoney(MoneyTransferDTO moneyTransferDTO, String email) {
        // Trouver le compte du debiteur et debiter le montant de la transaction sans frais
        final User debtor = findUserByEmail(moneyTransferDTO.getContactEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Contact not found with email : "+ moneyTransferDTO.getContactEmail()))
                .creditBalance(moneyTransferDTO.getAmount());
        userRepository.save(debtor);

        // Trouver le compte du crediteur et le montant debiter de la transaction avec les frais
        final User creditor = findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Compte not found with email : " + email))
                .debitBalance(moneyTransferDTO.getAmountWithFee());
        userRepository.save(creditor);

        Transaction transaction = new Transaction(moneyTransferDTO, creditor, debtor);
        return transactionRepository.save(transaction);
    }
}
