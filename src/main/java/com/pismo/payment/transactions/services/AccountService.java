package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.dtos.in.AccountRequest;
import com.pismo.payment.transactions.dtos.out.AccountResponse;
import com.pismo.payment.transactions.exceptions.AccountException;
import com.pismo.payment.transactions.exceptions.AccountNotFoundException;
import com.pismo.payment.transactions.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse getAccountByAccountId(final Long accountId) {
        var account = accountRepository.findAccountByAccountId(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found."));

        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .documentNumber(account.getDocumentNumber())
                .build();
    }

    public AccountResponse getAccount(final String documentNumber) {
        var account = accountRepository.findByDocumentNumber(documentNumber).orElseThrow(() -> new AccountNotFoundException("Account not found."));

        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .documentNumber(account.getDocumentNumber())
                .build();
    }

    public AccountResponse createAccount(final AccountRequest accountRequest) {
        var account = accountRepository.findByDocumentNumber(accountRequest.documentNumber());
        if (account.isPresent())
            throw new AccountException("Account already exists.");

        var newAccount = accountRepository.save(Account.builder().documentNumber(accountRequest.documentNumber()).build());

        return AccountResponse.builder()
                .accountId(newAccount.getAccountId())
                .documentNumber(newAccount.getDocumentNumber())
                .build();
    }
}
