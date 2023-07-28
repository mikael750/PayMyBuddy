package com.paymybuddy.application;

import com.paymybuddy.application.DTO.MoneyTransferDTO;
import com.paymybuddy.application.controllers.HomeController;
import com.paymybuddy.application.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;
    @Mock
    private Model model;
    @Mock
    private Principal principal;
    @Mock
    private UserService userService;

    @Test
    public void showHomePageTest(){
        //GIVEN
        String expectedString= "<200 OK OK,home,[]>";

        //WHEN
        String actualString = homeController.homePage(model, principal, new MoneyTransferDTO()).toString();

        //THEN
        assertEquals(expectedString, actualString);
    }
}
