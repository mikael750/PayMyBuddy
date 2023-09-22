package com.paymybuddy.application.services;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.DTO.MoneyTransferDTO;
import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.models.Account;
import com.paymybuddy.application.models.Transaction;
import com.paymybuddy.application.models.User;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Trouve un utilisateur grace a son email
     * @param email email pour trouver l'utilisateur
     * @return l'utilisateur
     */
    Optional<User> findUserByEmail(String email);

    /**
     * sauvegarde un utilisateur dans la DataBase
     * @param userDto l'information du nouveau user
     * @return retourne l'user
     */
    User saveUser(UserDTO userDto);

    /**
     * Trouve la ContactList d'un utilisateur
     * @param email l'email utiliser pour trouver la ContactList
     * @return une liste de contact
     */
    List<ContactDTO> findContactList(String email);


    /**
     * Ajoute un contact a un utilisateur selon son email
     * @param contactDto l'info du contact a ajouter, qui est seulement l'email et le fullName
     * @param email l'email de l'utilisateur qu'on ajoute un contact dans leur liste
     * @return le compte d'utilisateur du contact
     */
    User addContact(ContactDTO contactDto, String email);

    /**
     * Trouve la solde d'un utilisateur
     * @param email email de l'utilisateur
     * @return retourne la solde de l'utilisateur
     */
    BigDecimal getBalance(String email);

    /**
     * Change la solde de l'utilisateur depuis son compte bancaire
     * @param accountDto l'info bancaire de la transaction
     * @param email l'email de l'utilisateur
     * @return la transaction
     */
    User bankTransfer(AccountDTO accountDto, String email);

    /**
     * credite la solde du debiteur, debite la solde du creancier et cree une nouvelle transaction dans la DataBase
     * @param moneyTransferDTO l'info de la transaction
     * @param email l'email du crediteur
     * @return retourne la transaction
     */
    Transaction transferMoney(MoneyTransferDTO moneyTransferDTO, String email);

    /**
     * Renvoie le compte bancaire de l'utilisateur
     * @param user utilisateur pour lequel le compte bancaire est necessaire
     * @return retourne le compte bancaire de l'utilisateur s'il existe, sinon c'est vide
     */
     Optional<Account> getUserBankAccount(User user);

    /**
     * Depose de l'argent sur le comte d'un utilisateur
     */
    @Transactional
    void deposit(User user, String amount);

    /**
     * Retire de l'argent sur le compte de l'utilisateur
     */
    @Transactional
    void withdraw(User user, String amount);
}
