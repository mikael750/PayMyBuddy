package com.paymybuddy.application;

import com.paymybuddy.application.controllers.AuthController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
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
        String expectedString = "<200 OK OK,login,[]>";

        //WHEN
        String actualString = authController.loginPage().toString();

        //THEN
        assertEquals(expectedString, actualString);
    }

    @Test
    public void registrationPageTest(){
        //GIVEN
        String expectedString = "<200 OK OK,registration,[]>";

        //WHEN
        String actualString = authController.registrationPage(model).toString();

        //THEN
        assertEquals(expectedString, actualString);
    }
}
