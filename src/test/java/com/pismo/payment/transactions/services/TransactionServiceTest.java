package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.domain.operationType.OperationType;
import com.pismo.payment.transactions.domain.transaction.Transaction;
import com.pismo.payment.transactions.dtos.in.TransactionRequest;
import com.pismo.payment.transactions.dtos.out.AccountResponse;
import com.pismo.payment.transactions.dtos.out.TransactionResponse;
import com.pismo.payment.transactions.exceptions.AccountException;
import com.pismo.payment.transactions.exceptions.OperationTypeException;
import com.pismo.payment.transactions.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private OperationTypeService operationTypeService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateANewTransactionWithSuccess() throws Exception {
        //Arrange
        var transactionRequest = new TransactionRequest(new BigDecimal("100.0"), 1L, 1L);
        var accountResponse = AccountResponse.builder().account_id(1L).build();
        var operationType = OperationType.builder().operationTypeId(1L).build();

        when(accountService.getAccountByAccountId(anyLong())).thenReturn(accountResponse);
        when(operationTypeService.getOperationTypeByOperationTypeId(anyLong())).thenReturn(operationType);
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction savedTransaction = invocation.getArgument(0);
            savedTransaction.setTransactionId(1L);
            return savedTransaction;
        });

        //Act
        var response = transactionService.createTransaction(transactionRequest);

        //Assert
        assertNotNull(response);
        assertEquals(1L, response.operation_type_id());
        assertEquals(1L, response.account_id());
        assertEquals(new BigDecimal("100.0"), response.amount());
        assertNotNull(response.event_date());
    }

    @Test
    public void shouldThrowAccountExceptionWhenAccountNotFound() {
        //Arrange
        var transactionRequest = new TransactionRequest(new BigDecimal("100.0"), 1L, 1L);

        when(accountService.getAccountByAccountId(anyLong())).thenReturn(null);

        //Act
        var thrown = assertThrows(AccountException.class, () -> transactionService.createTransaction(transactionRequest));

        //Assert
        assertEquals("Account not found.", thrown.getMessage());
    }

    @Test
    public void shouldOperationTypeExceptionWhenOperationTypeDoesNotExist() {
        //Arrange
        var transactionRequest = new TransactionRequest(new BigDecimal("100.0"), 1L, 1L);
        var accountResponse = AccountResponse.builder().account_id(1L).build();

        when(accountService.getAccountByAccountId(anyLong())).thenReturn(accountResponse);
        when(operationTypeService.getOperationTypeByOperationTypeId(anyLong())).thenReturn(null);

        //Act
        var thrown = assertThrows(OperationTypeException.class, () -> transactionService.createTransaction(transactionRequest));

        //Assert
        assertEquals("Operation type does not exist.", thrown.getMessage());
    }
}