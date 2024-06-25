package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.domain.operationType.OperationType;
import com.pismo.payment.transactions.dtos.in.TransactionRequest;
import com.pismo.payment.transactions.exceptions.AccountException;
import com.pismo.payment.transactions.exceptions.OperationTypeException;
import com.pismo.payment.transactions.repositories.OperationTypeRepository;
import com.pismo.payment.transactions.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class OperationTypeServiceTest {

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @InjectMocks
    private OperationTypeService operationTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAValidOperationType() {
        //Arrange
        var mockOperationType = OperationType.builder().operationTypeId(1L).description("COMPRA A VISTA").build();

        when(operationTypeRepository.findByOperationTypeId(anyLong())).thenReturn(Optional.of(mockOperationType));

        //Act
        var result = operationTypeService.getOperationTypeByOperationTypeId(mockOperationType.getOperationTypeId());

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getOperationTypeId());
        assertEquals("COMPRA A VISTA", result.getDescription());
    }

    @Test
    void shouldThrowOperationTypeNotFoundExceptionWhenOperationTypeIsInvalid() {
        //Arrange
        when(operationTypeService.getOperationTypeByOperationTypeId(anyLong())).thenReturn(null);

        //Assert
        var thrown = assertThrows(AccountException.class, () -> operationTypeService.getOperationTypeByOperationTypeId(5L));

        //Assert
        assertEquals("OperationType invalid.", thrown.getMessage());
    }
}