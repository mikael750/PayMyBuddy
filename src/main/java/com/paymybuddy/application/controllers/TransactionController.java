package com.paymybuddy.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class TransactionController {

    @GetMapping(value = "/transaction")
    public String transactionPage(){return "transaction";}
}
