package com.paymybuddy.application.services.Impl;

import com.paymybuddy.application.repository.UserAccountRepository;
import com.paymybuddy.application.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;
}
