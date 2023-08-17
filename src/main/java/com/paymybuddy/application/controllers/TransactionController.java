package com.paymybuddy.application.controllers;

import com.paymybuddy.application.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/transaction")
    public String transactionPage(){return "transaction";}
}
