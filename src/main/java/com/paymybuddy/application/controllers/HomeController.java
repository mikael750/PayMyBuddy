package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.DTO.MoneyTransferDTO;
import com.paymybuddy.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/home")
    public ResponseEntity<String> homePage(Model homeFormDto, Principal principal, MoneyTransferDTO moneyTransferDTO){
        BigDecimal balance = userService.getBalance(principal.getName());
        List<ContactDTO> contactDtoList = userService.findContactList(principal.getName());
        homeFormDto.addAttribute("money_transfer", moneyTransferDTO);
        homeFormDto.addAttribute("contact_list", contactDtoList);
        homeFormDto.addAttribute("solde", balance);
        return ResponseEntity.ok("home");
    }

    @PostMapping(value = "/home")
    public String moneyTransfer(){
        return "";
    }
}
