package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.DTO.MoneyTransferDTO;
import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.models.Account;
import com.paymybuddy.application.models.Transaction;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.TransactionRepository;
import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


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
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        if (accountDto.getAmount().toBigInteger().intValueExact() < 0){
            withdraw(userAccount, String.valueOf(accountDto.getAmount().toString()));
        }else{
            deposit(userAccount, String.valueOf(accountDto.getAmount().toString()));
        }

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
                .orElseThrow(() -> new UsernameNotFoundException("Account not found with email : " + email))
                .debitBalance(moneyTransferDTO.getAmountWithFee());
        userRepository.save(creditor);

        Transaction transaction = new Transaction(moneyTransferDTO, creditor, debtor);
        return transactionRepository.save(transaction);
    }

    /**
     * Returns provided user's bank account.
     *
     * @param user
     *         user for which the bank account is needed.
     *
     * @return user bank account if exists, empty optional otherwise.
     */
    public Optional<Account> getUserBankAccount(User user) {
        Assert.notNull(user, "User must not be null");
        return accountRepository.findByUser(user);
    }


    /**
     * Deposits money to buddy account.
     */
    @Transactional
    public void deposit(User user, String amount) {
        Assert.notNull(user, "User must not be null");
        // if amount is still not valid after interface's validator, remove any negative signs
        amount = amount.replace("-", "");
        // find user's bank account
        Optional<Account> bankAccount = accountRepository.findByUser(user);
        // deposit cannot be done if the user does not own a bank account
        if (bankAccount.isEmpty()) {
            throw new RuntimeException("The user does not have any bank account. Deposit can not be proceeded.");
        }
        // Subtract amount to deposit from bank account
        bankAccount.get().setAmount(bankAccount.get().getAmount().subtract(new BigDecimal(amount)));
        // add amount to deposit to user's account
        user.setSolde(user.getSolde().add(new BigDecimal(amount)));
        userRepository.save(user);
    }

    /**
     * Withdraws money to user's bank account.
     */
    @Transactional
    public void withdraw(User user, String amount) {
        Assert.notNull(user, "User must not be null");
        // if amount is still not valid after interface's validator, remove any negative signs
        amount = amount.replace("-", "");
        // find user's bank account
        Optional<Account> bankAccount = accountRepository.findByUser(user);
        // withdraw cannot be done if the user does not own a bank account
        if (bankAccount.isEmpty()) {
            throw new RuntimeException("The user does not have any bank account. Withdrawal can not be " + "proceeded.");
        }
        // add amount to deposit from bank account
        bankAccount.get().setAmount(bankAccount.get().getAmount().add(new BigDecimal(amount)));
        // subtract amount to deposit to user's account
        user.setSolde(user.getSolde().subtract(new BigDecimal(amount)));
        userRepository.save(user);
    }
}
