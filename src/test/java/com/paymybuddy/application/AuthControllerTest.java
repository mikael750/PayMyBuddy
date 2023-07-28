package com.paymybuddy.application;

import com.paymybuddy.application.controllers.AuthController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;
    @Mock
    private Model model;

    @Test
    public void loginPageTest(){
        //GIVEN
        String expectedString = "login";

        //WHEN
        String actualString = authController.loginPage();

        //THEN
        assertEquals(expectedString, actualString);
    }

    @Test
    public void registrationPageTest(){
        //GIVEN
        String expectedString = "registration";

        //WHEN
        String actualString = authController.registrationPage(model);

        //THEN
        assertEquals(expectedString, actualString);
    }
}
