package com.paymybuddy.application.services;

import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.models.User;

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

}
