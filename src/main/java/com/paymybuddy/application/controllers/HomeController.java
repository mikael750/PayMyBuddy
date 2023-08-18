package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.DTO.MoneyTransferDTO;
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
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/home")
    public String homePage(Model homeFormDto, Authentication principal, MoneyTransferDTO moneyTransferDTO){
        BigDecimal balance = userService.getBalance(principal.getName());
        List<ContactDTO> contactDtoList = userService.findContactList(principal.getName());
        homeFormDto.addAttribute("money_transfer", moneyTransferDTO);
        homeFormDto.addAttribute("contact_list", contactDtoList);
        homeFormDto.addAttribute("solde", balance);
        return "home";
    }

    @PostMapping(value = "/home")
    public String moneyTransfer(@Valid @ModelAttribute("money_transfer")MoneyTransferDTO moneyTransferDTO , BindingResult result, Model moneyTransferFormDTO, Authentication principal){
        if (result.hasErrors()) {
            moneyTransferFormDTO.addAttribute("money_transfer", moneyTransferDTO);
            return homePage(moneyTransferFormDTO, principal, moneyTransferDTO);
        }
        try{
            userService.transferMoney(moneyTransferDTO, principal.getName());
        }catch (Exception e){
            moneyTransferFormDTO.addAttribute("message_error", e.getMessage());
            return homePage(moneyTransferFormDTO, principal, moneyTransferDTO);
        }
        return "redirect:/home?success";
    }
}
