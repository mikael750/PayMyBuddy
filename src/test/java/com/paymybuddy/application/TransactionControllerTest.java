package com.paymybuddy.application;

import com.paymybuddy.application.DTO.TransactionDTO;
import com.paymybuddy.application.controllers.TransactionController;
import com.paymybuddy.application.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionControllerTest {
    @InjectMocks
    private TransactionController transactionController;
    @Mock
    private TransactionService transactionService;
    @Mock
    private Model model;
    @Mock
    private Authentication principal;

    @Test
    public void transactionPageTest(){
        //GIVEN
        TransactionDTO transactionDto = new TransactionDTO( BigDecimal.ONE, "test", "test", "test",LocalDate.now());
        Page<TransactionDTO> transactionDtoPage = new PageImpl(List.of(transactionDto), PageRequest.of(1,5), 1);
        when(principal.getName()).thenReturn("test");
        when(transactionService.getPage(PageRequest.of(0,5), "test")).thenReturn(transactionDtoPage);
        String expectedString = "transaction";

        //WHEN
        String actualString = transactionController.transactionPage(model, principal, 1, 5).toString();

        //THEN
        assertEquals(expectedString, actualString);
        verify(model, times(1)).addAttribute("pageNumbers", List.of(1,2));
    }
}
