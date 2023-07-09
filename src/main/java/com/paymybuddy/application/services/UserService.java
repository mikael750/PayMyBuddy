package com.paymybuddy.application.services;

import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.models.User;

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

}
