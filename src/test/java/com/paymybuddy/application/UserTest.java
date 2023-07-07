package com.paymybuddy.application;

import com.paymybuddy.application.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {

    @Mock
    private static User existingUser;

    @BeforeEach
    public void setUp(){
        existingUser = new User(1, "Jack", "Frost", "test@test.fr", "testMdp", "01/01/2000", BigDecimal.ZERO, null);

    }

    @Test
    public void getFullNameTest(){
        //GIVEN
        String expectedFullName = existingUser.getFirstName() + " " + existingUser.getLastName();

        //WHEN
        String actualFullName = existingUser.getFullName();

        //THEN
        assertEquals(expectedFullName, actualFullName);
    }

    @Test
    public void creditBalance(){
        //GIVEN

        //WHEN
        existingUser.creditBalance(BigDecimal.valueOf(500));
        //THEN
        assertEquals(BigDecimal.valueOf(500),existingUser.getSolde());

    }
}
