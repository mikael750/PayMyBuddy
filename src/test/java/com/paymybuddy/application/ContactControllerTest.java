package com.paymybuddy.application;

import com.paymybuddy.application.controllers.ContactController;
import com.paymybuddy.application.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContactControllerTest {

    @InjectMocks
    private ContactController contactController;
    @Mock
    private Model model;
    @Mock
    private Authentication principal;
    @Mock
    private UserService userService;

    @Test
    public void contactPageTest(){
        //GIVEN
        String expectedString= "<200 OK OK,contact,[]>";

        //WHEN
        String actualString = contactController.contactPage(model, principal).toString();

        //THEN
        assertEquals(expectedString, actualString);
    }
}
