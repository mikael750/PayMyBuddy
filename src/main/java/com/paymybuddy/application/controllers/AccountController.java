package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.services.AccountService;
import com.paymybuddy.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value =  {"/account"})
    public String accountPage(Model accountFormDto, Authentication principal, AccountDTO accountDTO){
        BigDecimal balance = userService.getBalance(principal.getName());
        accountFormDto.addAttribute("transfer_amount", accountDTO);
        accountFormDto.addAttribute("balance", balance);
        accountFormDto.addAttribute("add_account", new AccountDTO());
       // accountFormDto.addAttribute("account_list", accountService.findAccountList(principal.getName()));
        return "account";
    }

    @PostMapping("/account")
    public String account(@Valid @ModelAttribute("transfer_amount") AccountDTO accountDTO, BindingResult result, Model accountFormDTO, Authentication principal){
        BigDecimal balance = userService.getBalance(principal.getName());
        if (result.hasErrors()) {
            accountFormDTO.addAttribute("transfer_amount", accountDTO);
            accountFormDTO.addAttribute("balance", balance);
            return "account";
        }
        try {
            userService.bankTransfer(accountDTO, principal.getName());
            return "redirect:/account?success";
        } catch (Exception e) {
            accountFormDTO.addAttribute("transfer_amount", accountDTO);
            accountFormDTO.addAttribute("balance", balance);
            result.rejectValue("amount", null, e.getMessage());
            return "account";
        }
    }
/*
    @PostMapping("/account/save")
    public String addAccount(@Valid @ModelAttribute("add_account") AccountDTO accountDTO, BindingResult result, Model accountFormDto, Authentication principal){
        if (result.hasErrors()) {
            return accountPage(accountFormDto, principal, accountDTO);
        }
        try {
            accountService.addAccount(accountDTO, principal.getName());
        } catch (Exception e) {
            accountFormDto.addAttribute("account_error", e.getMessage());
            return accountPage(accountFormDto, principal, accountDTO);
     }*/

}
