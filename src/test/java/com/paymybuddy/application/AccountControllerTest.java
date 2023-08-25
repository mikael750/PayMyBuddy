package com.paymybuddy.application;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.controllers.AccountController;
import com.paymybuddy.application.models.Account;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.services.AccountService;
import com.paymybuddy.application.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;
    @Mock
    private Model model;
    @Mock
    private Authentication principal;
    @Mock
    private UserService userService;
    @Mock
    private BindingResult result;
    @Mock
    private AccountService accountService;

    @Test
    public void accountPageTest(){
        //GIVEN
        String expectedString = "account";

        //WHEN
        String actualString = accountController.accountPage(model, principal, new AccountDTO()).toString();

        //THEN
        assertEquals(expectedString, actualString);
    }

    @Test
    public void accountTest(){
        //GIVEN
        when(result.hasErrors()).thenReturn(false);
        when(userService.bankTransfer(any(AccountDTO.class), anyString())).thenReturn(new User());
        String expectedString = "redirect:/account?success";

        //WHEN
        String actualString = accountController.account(new AccountDTO(), result, model, principal);

        //THEN
        assertEquals(expectedString, actualString);
    }
/*
    @Test
    public void addContactTest(){
        //GIVEN
        AccountDTO accountDTO = new AccountDTO("test","test", BigDecimal.valueOf(1000.00));
        when(accountService.addAccount(accountDTO, "test")).thenReturn(new Account());
        String expectedString = "redirect:/account?success";
        when(principal.getName()).thenReturn("test");

        //WHEN
        String actualString = accountController.addAccount(accountDTO, result, model, principal);

        //THEN
        assertEquals(expectedString, actualString);
        verify(accountService, times(1)).addAccount(accountDTO, "test");
    }*/
}
