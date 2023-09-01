package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddAccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = {"/addaccount"})
    public String addAccountPage(Model addAccountFormDto, AccountDTO accountDTO) {
        //addAccountFormDto.addAttribute("account_list", accountService.findAccountList(accountDTO.getIban()));//principal.getName()));
        addAccountFormDto.addAttribute("add_account", new AccountDTO());
        return "addaccount";
    }


    @PostMapping("/addaccount")
    public String addAccount(@Valid @ModelAttribute("add_account") AccountDTO accountDTO, BindingResult result, Model addAccountFormDto, Authentication principal) {
        if (result.hasErrors()) {
            return addAccountPage(addAccountFormDto, accountDTO);
        }
        try {
            accountService.addAccount(accountDTO, accountDTO.getIban());
        } catch (Exception e) {
            addAccountFormDto.addAttribute("account_error", e.getMessage());
            return addAccountPage(addAccountFormDto, accountDTO);
        }
        return "redirect:/addaccount?success";

    }
}