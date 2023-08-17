package com.paymybuddy.application;

import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.controllers.ContactController;
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
import static org.mockito.Mockito.*;

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
    @Mock
    private BindingResult result;

    @Test
    public void contactPageTest(){
        //GIVEN
        String expectedString= "<200 OK OK,contact,[]>";

        //WHEN
        String actualString = contactController.contactPage(model, principal).toString();

        //THEN
        assertEquals(expectedString, actualString);
    }

    @Test
    public void addContactTest(){
        //GIVEN
        ContactDTO contactDTO = new ContactDTO("test", "test");
        when(userService.addContact(contactDTO, "test")).thenReturn(new User());
        String expectedString = "<200 OK OK,redirect:/contact?success,[]>";
        when(principal.getName()).thenReturn("test");

        //WHEN
        String actualString = contactController.addContact(contactDTO, result, model, principal).toString();

        //THEN
        assertEquals(expectedString, actualString);
        verify(userService, times(1)).addContact(contactDTO, "test");
    }
}
