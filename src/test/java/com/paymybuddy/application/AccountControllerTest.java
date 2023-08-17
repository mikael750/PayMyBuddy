package com.paymybuddy.application;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.controllers.AccountController;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

    @Test
    public void accountPageTest(){
        //GIVEN
        String expectedString = "<200 OK OK,account,[]>";

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
        String expectedString = "<200 OK OK,redirect:/account?success,[]>";

        //WHEN
        String actualString = accountController.account(new AccountDTO(), result, model, principal).toString();

        //THEN
        assertEquals(expectedString, actualString);
    }
}
