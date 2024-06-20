package com.pismo.payment.transactions.controllers;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.dtos.AccountDTO;
import com.pismo.payment.transactions.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDto) throws Exception {
        var account = accountService.createAccount(accountDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{account_id}")
    @ResponseBody
    public ResponseEntity<Account> getAccountByAccountId(@PathVariable Long account_id) throws Exception {
        var account = accountService.getAccountByAccountId(account_id);
        if (account == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
