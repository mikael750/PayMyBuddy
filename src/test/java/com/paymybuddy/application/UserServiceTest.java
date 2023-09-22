package com.paymybuddy.application;

import com.paymybuddy.application.DTO.AccountDTO;
import com.paymybuddy.application.DTO.ContactDTO;
import com.paymybuddy.application.DTO.MoneyTransferDTO;
import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.models.Account;
import com.paymybuddy.application.models.Transaction;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.TransactionRepository;
import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void saveUserTest() {
        //GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("test");
        userDTO.setLastName("test");
        userDTO.setEmail("test@test.test");
        userDTO.setPassword("test");

        //WHEN
        userService.saveUser(userDTO);

        //THEN
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void findUserByEmailTest() {
        //GIVEN

        //WHEN
        userService.findUserByEmail("test");

        //THEN
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    public void findContactListTest() {
        //GIVEN
        User user = new User();
        User contact = new User();
        user.getContacts().add(contact);
        List<ContactDTO> expectedContactList = List.of(new ContactDTO(contact));
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        //WHEN
        List<ContactDTO> contactList = userService.findContactList("test");

        //THEN
        assertEquals(expectedContactList,contactList);
    }

    @Test
    public void addContactTest() {
        //GIVEN
        User user = new User(1, "test", "test", "test", "test", "test", BigDecimal.ONE, new ArrayList<>());
        User contact = new User(2, "contact", "contact", "contact", "contact", "contact", BigDecimal.ONE, null);
        ContactDTO contactDto = new ContactDTO("contact","contact");
        when(userRepository.findByEmail("test")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("contact")).thenReturn(Optional.of(contact));

        //WHEN
        userService.addContact(contactDto,"test");

        //THEN
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getBalanceTest(){
        //GIVEN
        User user = new User(1, "test", "test", "test", "test", "test", BigDecimal.ONE, null);
        when(userRepository.findByEmail("test")).thenReturn(Optional.of(user));

        //WHEN
        BigDecimal actualBalance = userService.getBalance("test");

        //THEN
        assertEquals(BigDecimal.ONE, actualBalance);
    }
/*
    @Test
    public void bankTransferTest(){
        //GIVEN
        User user = new User(1, "test", "test", "test", "test", "test", BigDecimal.ZERO, null);
        User expectedUserAccount = new User(1, "test", "test", "test", "test", "test", BigDecimal.valueOf(100), null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user)).thenReturn(Optional.of(user));
        AccountDTO accountDTO = new AccountDTO(user,"test","test",BigDecimal.valueOf(100));
        when(userRepository.save(expectedUserAccount)).thenReturn(expectedUserAccount);

        Account account = new Account(accountDTO);
        account.setId(1);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountRepository.findByIban(anyString())).thenReturn(Optional.of(account)).thenReturn(Optional.of(account));

        //WHEN
        userService.bankTransfer(accountDTO, "test");

        //THEN
        verify(userRepository, times(1)).save(expectedUserAccount);
    }
*/
    @Test
    public void transferMoneyTest(){
        //GIVEN
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO("contact", BigDecimal.valueOf(75));
        User debtor = new User(1, "contact", "contact", "contact", "contact", "contact", BigDecimal.ZERO, null);
        User creditor = new User(2, "test", "test", "test", "test", "test", BigDecimal.valueOf(100), null);
        Transaction expectedTransaction = new Transaction(moneyTransferDTO, creditor, debtor);

        when(userRepository.findByEmail("test")).thenReturn(Optional.of(creditor));
        when(userRepository.findByEmail("contact")).thenReturn(Optional.of(debtor));
        when(userRepository.save(any(User.class))).thenAnswer(a -> a.getArguments()[0]);
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(a -> a.getArguments()[0]);


        //WHEN
        Transaction response = userService.transferMoney(moneyTransferDTO, "test");

        //THEN
        assertThat(response)
                .satisfies(responses -> {
                    assertThat(response.getSender().getId()).isEqualTo(creditor.getId());
                    assertThat(response.getSender().getSolde()).isEqualTo(BigDecimal.valueOf(100).subtract(moneyTransferDTO.getAmountWithFee()));

                    assertThat(response.getReceiver().getId()).isEqualTo(debtor.getId());
                    assertThat(response.getReceiver().getSolde()).isEqualTo(moneyTransferDTO.getAmount());
                });
        verify(userRepository, times(2)).save(any(User.class));
        verify(transactionRepository, times(1)).save(expectedTransaction);
    }
}
