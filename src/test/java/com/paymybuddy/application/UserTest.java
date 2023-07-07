package com.paymybuddy.application;

import com.paymybuddy.application.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {


    private static User existingUser1, existingUser2;

    @BeforeEach
    public void setUp(){
        existingUser1 = new User(1, "Jack", "Frost", "test@test.fr", "testMdp", "01/01/2000", BigDecimal.ZERO, new ArrayList<>());
        existingUser2 = new User(2, "John", "Dujardin", "test@test.fr", "testMdp", "01/01/2000", BigDecimal.ONE, null);

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
    public void creditBalanceTest(){
        //GIVEN

        //WHEN
        existingUser1.creditBalance(BigDecimal.valueOf(500));
        //THEN
        assertEquals(BigDecimal.valueOf(500),existingUser1.getSolde());

    }

    @Test
    public void debitSoldeTest(){
        //GIVEN

        //WHEN
        existingUser2.debitBalance(BigDecimal.ONE);

        //THEN
        assertEquals(BigDecimal.ZERO, existingUser2.getSolde());

    }

    @Test
    public void addContactTest(){
        //GIVEN

        //WHEN
        existingUser1.addContact(existingUser2);

        //THEN
        assertEquals(existingUser2, existingUser1.getContactList().get(0));

    }
}
