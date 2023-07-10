package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.models.User;
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
            throw new RuntimeException("Cette email = " + userDto.getEmail() + " est deja utiliser");
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
            throw new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email);
        }
        return userGetContact.get().getContactList()
                .stream().map(ContactDTO::new)
                .toList();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public User addContact(ContactDTO contactDto, String email) {
        User contactToAdd = userRepository.findByEmail(contactDto.getEmail())
                      .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email));

        User userAccountToAddContact = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email))
                .addContact(contactToAdd);

        return userRepository.save(userAccountToAddContact);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public BigDecimal getBalance(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email))
                .getSolde();
    }

}
