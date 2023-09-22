package com.paymybuddy.application;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.models.Account;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.services.Impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;
/*
    @Test
    public void saveAccountTest() {
        //GIVEN
        Account account = new Account(1,"testiban","testbic", BigDecimal.valueOf(10.0), new ArrayList<>());
        //Account contact = new Account(2, "contact", "contact", "contact", "contact", "contact", BigDecimal.ONE, null);
        AccountDTO accountDto = new AccountDTO();
        when(accountRepository.findByIban("testiban")).thenReturn(Optional.of(account));
        //when(accountRepository.findByIban("contact")).thenReturn(Optional.of(contact));

        //WHEN
        accountService.saveAccount(accountDto);

        //THEN
        verify(accountRepository, times(1)).save(account);
    }*/
/*
    @Test
    public void findAccountListTest() throws Exception {
        //GIVEN
        Account account = new Account();
        Account bank = new Account();
        account.getAccounts().add(bank);
        List<AccountDTO> expectedAccountList = List.of(new AccountDTO(bank));
        when(accountRepository.findByIban(anyString())).thenReturn(Optional.of(account));

        //WHEN
        List<AccountDTO> accountList = accountService.findAccountList("test");

        //THEN
        assertEquals(expectedAccountList,accountList);
    }*/
}
