package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping(value =  {"/account"})
    public ResponseEntity<String> accountPage(Model accountFormDto,Authentication principal, AccountDTO accountDTO){
        BigDecimal balance = userService.getBalance(principal.getName());
        accountFormDto.addAttribute("transfer_amount", accountDTO);
        accountFormDto.addAttribute("balance", balance);
        return ResponseEntity.ok("account");
    }

    @PostMapping("/account")
    public ResponseEntity<String> account(@Valid @ModelAttribute("transfer_amount") AccountDTO accountDTO, BindingResult result, Model accountFormDTO, Authentication principal){
        BigDecimal balance = userService.getBalance(principal.getName());
        if (result.hasErrors()) {
            accountFormDTO.addAttribute("transfer_amount", accountDTO);
            accountFormDTO.addAttribute("balance", balance);
            return ResponseEntity.ok("account");
        }
        try {
            userService.bankTransfer(accountDTO, principal.getName());
            return ResponseEntity.ok("redirect:/account?success");
        } catch (Exception e) {
            accountFormDTO.addAttribute("transfer_amount", accountDTO);
            accountFormDTO.addAttribute("balance", balance);
            result.rejectValue("amount", null, e.getMessage());
            return ResponseEntity.ok("account");
        }
    }
}
