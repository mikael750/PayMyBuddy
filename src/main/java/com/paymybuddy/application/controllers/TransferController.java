package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.DTO.MoneyTransferDTO;
import com.paymybuddy.application.DTO.TransactionDTO;
import com.paymybuddy.application.services.TransactionService;
import com.paymybuddy.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TransferController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/transfer")
    public String homePage(Model homeFormDto, Authentication principal, MoneyTransferDTO moneyTransferDTO , @RequestParam(name = "page", required = false, defaultValue = "1") Integer currentPage, @RequestParam(name = "size", required = false, defaultValue = "5") Integer pageSize){
        Page<TransactionDTO> transactionDTOPage = transactionService.getPage(PageRequest.of(currentPage - 1, pageSize), principal.getName());
        homeFormDto.addAttribute("transactionDTOPage", transactionDTOPage);
        int totalPages = transactionDTOPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            homeFormDto.addAttribute("pageNumbers", pageNumbers);
        }
        BigDecimal balance = userService.getBalance(principal.getName());
        List<ContactDTO> contactDtoList = userService.findContactList(principal.getName());
        homeFormDto.addAttribute("money_transfer", moneyTransferDTO);
        homeFormDto.addAttribute("contact_list", contactDtoList);
        homeFormDto.addAttribute("solde", balance);
        return "transfer";
    }

    @PostMapping(value = "/transfer")
    public String moneyTransfer(@Valid @ModelAttribute("money_transfer")MoneyTransferDTO moneyTransferDTO , BindingResult result, Model moneyTransferFormDTO, Authentication principal){
        if (result.hasErrors()) {
            moneyTransferFormDTO.addAttribute("money_transfer", moneyTransferDTO);
            return homePage(moneyTransferFormDTO, principal, moneyTransferDTO,1,1);
        }
        try{
            userService.transferMoney(moneyTransferDTO, principal.getName());
        }catch (Exception e){
            moneyTransferFormDTO.addAttribute("message_error", e.getMessage());
            return homePage(moneyTransferFormDTO, principal, moneyTransferDTO,1,1);
        }
        return "redirect:/transfer?success";
    }
}
