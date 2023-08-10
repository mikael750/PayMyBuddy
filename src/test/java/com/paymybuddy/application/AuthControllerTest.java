package com.paymybuddy.application;

import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.controllers.AuthController;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;
    @Mock
    private Model model;
    @Mock
    private UserService userService;
    @Mock
    private BindingResult result;


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

    @Test
    public void registrationTest(){
        //GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setLastName("test");
        userDTO.setFirstName("test");
        userDTO.setEmail("test@test.test");
        userDTO.setBirthdate("01/01/2000");
        userDTO.setPassword("test");

        String expectedString = "<200 OK OK,redirect:/registration?success,[]>";

        when(userService.saveUser(userDTO)).thenReturn(new User());

        //WHEN
        String actualString = authController.registration(userDTO, result, model).toString();

        //THEN
        verify(userService, times(1)).saveUser(userDTO);
        assertEquals(expectedString, actualString);
    }
}
