package com.paymybuddy.application;

import com.paymybuddy.application.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {

    @Mock
    private static User existingUser1, existingUser2;

    @BeforeEach
    public void setUp(){
        existingUser1 = new User(1, "Jack", "Frost", "test@test.fr", "testMdp", "01/01/2000", BigDecimal.ZERO, null);
        existingUser2 = new User(1, "John", "Dujardin", "test@test.fr", "testMdp", "01/01/2000", BigDecimal.ONE, null);

    }

    @Test
    public void getFullNameTest(){
        //GIVEN
        String expectedFullName = existingUser1.getFirstName() + " " + existingUser1.getLastName();

        //WHEN
        String actualFullName = existingUser1.getFullName();

        //THEN
        assertEquals(expectedFullName, actualFullName);
    }

    @Test
    public void creditSoldeTest(){
        //GIVEN

        //WHEN
        existingUser1.creditSolde(BigDecimal.valueOf(500));
        //THEN
        assertEquals(BigDecimal.valueOf(500),existingUser1.getSolde());

    }

    @Test
    public void debitSoldeTest(){
        //GIVEN

        //WHEN
        existingUser2.debitSolde(BigDecimal.ONE);

        //THEN
        assertEquals(BigDecimal.ZERO, existingUser2.getSolde());

    }
}
