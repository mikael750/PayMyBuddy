package com.paymybuddy.application.controllers;

import com.paymybuddy.application.DTO.TransactionDTO;
import com.paymybuddy.application.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/transaction")
    public String transactionPage(Model transactionFormDTO, Authentication principal, @RequestParam(name = "page", required = false, defaultValue = "1") Integer currentPage, @RequestParam(name = "size", required = false, defaultValue = "5") Integer pageSize) {
        Page<TransactionDTO> transactionDTOPage = transactionService.getPage(PageRequest.of(currentPage - 1, pageSize), principal.getName());
        transactionFormDTO.addAttribute("transactionDTOPage", transactionDTOPage);

        int totalPages = transactionDTOPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            transactionFormDTO.addAttribute("pageNumbers", pageNumbers);
        }
        return "transaction";
    }
}
