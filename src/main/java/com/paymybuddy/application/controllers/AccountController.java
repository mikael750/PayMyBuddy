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
    public String accountPage(Model accountFormDTO, Authentication principal, AccountDTO accountDTO){
        BigDecimal balance = userService.getBalance(principal.getName());
        accountFormDTO.addAttribute("transfer_amount", accountDTO);
        accountFormDTO.addAttribute("list_account", accountService.getBankAccounts());
        accountFormDTO.addAttribute("balance", balance);
        return "account";
    }

    @PostMapping("/account")
    public String account(@Valid @ModelAttribute("transfer_amount") AccountDTO accountDTO, BindingResult result, Model accountFormDTO, Authentication principal){
        BigDecimal balance = userService.getBalance(principal.getName());
        if (result.hasErrors()) {
            accountFormDTO.addAttribute("list_account", accountService.getBankAccounts());
            accountFormDTO.addAttribute("transfer_amount", accountDTO);
            accountFormDTO.addAttribute("balance", balance);
            return "account";
        }
        try {
            userService.bankTransfer(accountDTO, principal.getName());
            return "redirect:/account?success";
        } catch (Exception e) {
            accountFormDTO.addAttribute("list_account", accountService.getBankAccounts());
            accountFormDTO.addAttribute("transfer_amount", accountDTO);
            accountFormDTO.addAttribute("balance", balance);
            result.rejectValue("amount", null, e.getMessage());
            return "account";
        }
    }
}
