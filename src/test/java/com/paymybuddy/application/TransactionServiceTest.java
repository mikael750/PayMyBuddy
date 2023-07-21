package com.paymybuddy.application;

import com.paymybuddy.application.DTO.TransactionDTO;
import com.paymybuddy.application.models.Transaction;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.repository.TransactionRepository;
import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.Impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    private BigDecimal amountWithFee(BigDecimal amount){
        return amount.multiply(BigDecimal.valueOf(0.005));
    }

    @Test
    public void findTransactionByUserTest(){
        //GIVEN
        User existingUser = new User(1, "test", "test", null, null, null, null, null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(existingUser));
        Transaction transaction = new Transaction(1, BigDecimal.ONE, amountWithFee(BigDecimal.ONE), null, existingUser, new User(), LocalDate.now());
        when(transactionRepository.findAllBySenderOrReceiverOrderByDateDesc(existingUser, existingUser)).thenReturn(List.of(transaction));
        List<TransactionDTO> expectedTransactionDTOList = List.of(new TransactionDTO(transaction));

        //WHEN
        List<TransactionDTO> actualTransactionDTOList = transactionService.findTransactionByEmail("test");

        //THEN
        assertEquals(expectedTransactionDTOList, actualTransactionDTOList);
    }

    @Test
    public void getPageWhenOnlyOnePageTest(){
        //GIVEN
        int currentPage = 1;
        int pageSize = 5;
        User userAccount = new User(1, "test", "test", "test", "test", "test", BigDecimal.ONE, null);
        Transaction transaction = new Transaction(1, BigDecimal.ONE, amountWithFee(BigDecimal.ONE), "test", new User(), new User(), LocalDate.now());
        when(userRepository.findByEmail("test")).thenReturn(Optional.of(userAccount));
        when(transactionRepository.findAllBySenderOrReceiverOrderByDateDesc(userAccount, userAccount)).thenReturn(List.of(transaction));
        TransactionDTO transactionDto = new TransactionDTO(transaction);
        Page<TransactionDTO> expectedTransactionDtoPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(currentPage, pageSize), List.of(transactionDto).size());

        //WHEN
        Page<TransactionDTO> actualTransactionDtoPage = transactionService.getPage(PageRequest.of(currentPage, pageSize), "test");

        //THEN
        assertEquals(expectedTransactionDtoPage, actualTransactionDtoPage);
    }

    @Test
    public void getPageWhenMoreThanOnePageTest(){
        //GIVEN
        int currentPage = 2;
        int pageSize = 5;
        User userAccount = new User(1, "test", "test", "test", "test", "test", BigDecimal.ONE, null);
        List<Transaction> transactionList = new ArrayList<>();
        List<TransactionDTO> transactionDtoList = new ArrayList<>();
        for(int i=0; i<7; i++ ){
            Transaction transaction = new Transaction(i, BigDecimal.ONE, amountWithFee(BigDecimal.ONE), "test", new User(), new User(), LocalDate.now());
            transactionList.add(transaction);
            transactionDtoList.add(new TransactionDTO(transaction));
        }
        when(userRepository.findByEmail("test")).thenReturn(Optional.of(userAccount));
        when(transactionRepository.findAllBySenderOrReceiverOrderByDateDesc(userAccount, userAccount)).thenReturn(transactionList);
        Page<TransactionDTO> expectedTransactionDtoPage = new PageImpl<>(transactionDtoList.subList(7, 7), PageRequest.of(currentPage, pageSize), transactionDtoList.size());

        //WHEN
        Page<TransactionDTO> actualTransactionDtoPage = transactionService.getPage(PageRequest.of(currentPage, pageSize), "test");

        //THEN
        assertEquals(expectedTransactionDtoPage, actualTransactionDtoPage);
    }

}
