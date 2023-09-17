package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.models.User;
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

import java.util.Optional;

@Controller
public class AddAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = {"/addaccount"})
    public String addAccountPage(Model addAccountFormDto, Authentication principal) {
        Integer userAccount =  userService.findUserByEmail(principal.getName()).get().getId();
        addAccountFormDto.addAttribute("user_account", userAccount);
        addAccountFormDto.addAttribute("add_account", new AccountDTO());
        return "addaccount";
    }


    @PostMapping("/addaccount")
    public String addAccount(@Valid @ModelAttribute("add_account") AccountDTO accountDTO, BindingResult result, Model addAccountFormDto, Authentication principal) {
        if (result.hasErrors()) {
            Integer userAccount =  userService.findUserByEmail(principal.getName()).get().getId();
            addAccountFormDto.addAttribute("user_account", userAccount);
            addAccountFormDto.addAttribute("add_account", accountDTO);
            return "addaccount";
        }

        try {
            accountService.saveAccount(accountDTO);
            return "redirect:/addaccount?success";
        } catch (Exception e) {
            result.rejectValue("iban", null, "An account already exist with the iban : " + accountDTO.getIban());
            return "addaccount";
        }
    }
}