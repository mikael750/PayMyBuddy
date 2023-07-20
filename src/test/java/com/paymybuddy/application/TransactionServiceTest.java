package com.paymybuddy.application;

import com.paymybuddy.application.DTO.MoneyTransferDTO;
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

import java.math.BigDecimal;
import java.time.LocalDate;
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
        List<TransactionDTO> actualTransactionDTOList = transactionService.findTransactionByUser("test");

        //THEN
        assertEquals(expectedTransactionDTOList, actualTransactionDTOList);
    }
}
