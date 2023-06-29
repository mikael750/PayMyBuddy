package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
}
