package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(UserDTO userDto) {
        Optional<User> existingUser = findUserByEmail(userDto.getEmail());
        if (existingUser.isPresent()){
            throw new RuntimeException("Cette email = " + userDto.getEmail() + " est deja utiliser");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userAccount = new User(userDto, passwordEncoder);
        return userRepository.save(userAccount);
    }
}
