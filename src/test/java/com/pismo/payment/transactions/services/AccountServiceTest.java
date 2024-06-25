package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.dtos.in.AccountRequest;
import com.pismo.payment.transactions.exceptions.AccountException;
import com.pismo.payment.transactions.exceptions.AccountNotFoundException;
import com.pismo.payment.transactions.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAnAccountWhenIdExists() {
        //Arrange
        var mockAccount = Account.builder().accountId(1L).documentNumber("10").build();

        //Act
        when(accountRepository.findAccountByAccountId(anyLong())).thenReturn(Optional.of(mockAccount));

        var result = accountService.getAccountByAccountId(mockAccount.getAccountId());

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.account_id());
        assertEquals("10", result.document_id());
    }

    @Test
    void shouldReturnAccountNotFoundWhenIdNotExists() {
        //Arrange
        when(accountRepository.findAccountByAccountId(anyLong())).thenReturn(Optional.empty());

        //Assert
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountByAccountId(1L));
    }

    @Test
    void shouldReturnAnExistingAccountWhenDocumentExists() {
        //Arrange
        var account = Account.builder().accountId(1L).documentNumber("10").build();
        when(accountRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(account));

        //Act
        var response = accountService.getAccount("10");

        //Assert
        assertNotNull(response);
        assertEquals(1L, response.account_id());
        assertEquals("10", response.document_id());
    }

    @Test
    void shouldReturnNotFoundAccountWhenDocumentNotExists() {
        //Arrange
        when(accountRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());

        //Assert
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccount("10"));
    }

    @Test
    public void shouldCreateANewAccountWithSuccess() {
        //Arrange
        var request = AccountRequest.builder().accountId(1L).build();
        var account = Account.builder().accountId(1L).documentNumber("10").build();
        when(accountRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        //Act
        var response = accountService.createAccount(request);

        //Assert
        assertNotNull(response);
        assertEquals(1L, response.account_id());
        assertEquals("10", response.document_id());
    }

    @Test
    public void shouldNotCreateANewAccountBecauseItsAlreadyExists() {
        //Arrange
        var request = AccountRequest.builder().documentNumber("10").build();
        var account = Account.builder().accountId(1L).documentNumber("10").build();
        when(accountRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(account));

        //Act
        assertThrows(AccountException.class, () -> accountService.createAccount(request));

    }
}