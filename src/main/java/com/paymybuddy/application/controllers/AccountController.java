package com.paymybuddy.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AccountController {

    @GetMapping(value =  {"/account"})
    public String accountPage(){
        return "account";
    }
}
