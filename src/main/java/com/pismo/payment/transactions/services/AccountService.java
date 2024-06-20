package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.dtos.AccountDTO;
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

    public Account getAccountByAccountId(final Long accountId) throws Exception {
        return accountRepository.findAccountByAccountId(accountId).orElseThrow(() -> new Exception("Account not found."));
    }

    public Account getAccount(final String documentNumber) throws Exception {
        return accountRepository.findByDocumentNumber(documentNumber).orElseThrow(() -> new Exception("Account not found."));
    }

    public Account createAccount(final AccountDTO accountDto) throws Exception {
        var account = accountRepository.findByDocumentNumber(accountDto.documentNumber());
        if (account.isPresent()) {
            throw new Exception("Account already exist.");
        }
        return accountRepository.save(Account.builder().documentNumber(accountDto.documentNumber()).build());
    }
}
