package com.pismo.payment.transactions.controllers;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.dtos.AccountDTO;
import com.pismo.payment.transactions.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/accounts")
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDto) {
        var account = accountService.createAccount(accountDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/account_id")
    public ResponseEntity<Account> createAccount(@RequestBody Long account_id) throws Exception {
        var account = accountService.getAccountByAccountId(account_id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
