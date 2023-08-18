package com.paymybuddy.application;

import com.paymybuddy.application.DTO.MoneyTransferDTO;
import com.paymybuddy.application.controllers.HomeController;
import com.paymybuddy.application.models.Transaction;
import com.paymybuddy.application.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;
    @Mock
    private Model model;
    @Mock
    private Authentication principal;
    @Mock
    private UserService userService;
    @Mock
    private BindingResult result;

    @Test
    public void showHomePageTest(){
        //GIVEN
        String expectedString= "home";

        //WHEN
        String actualString = homeController.homePage(model, principal, new MoneyTransferDTO());

        //THEN
        assertEquals(expectedString, actualString);
    }

    @Test
    public void moneyTransferTest(){
        //GIVEN
        when(principal.getName()).thenReturn("test");
        when(userService.transferMoney(new MoneyTransferDTO(), "test")).thenReturn(new Transaction());
        String expectedString = "redirect:/home?success";

        //WHEN
        String actualString = homeController.moneyTransfer(new MoneyTransferDTO(), result, model, principal);

        //THEN
        assertEquals(expectedString, actualString);
        verify(userService, times(1)).transferMoney(new MoneyTransferDTO(), "test");
    }
}
