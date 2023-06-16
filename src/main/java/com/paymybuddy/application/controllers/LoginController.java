package com.paymybuddy.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping(value =  {"/login"})
    public String loginPage(){
        return "login";
    }
}
