package com.pismo.payment.transactions.controllers;

import com.pismo.payment.transactions.dtos.in.AccountRequest;
import com.pismo.payment.transactions.dtos.out.AccountResponse;
import com.pismo.payment.transactions.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) throws Exception {
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{account_id}")
    @ResponseBody
    public ResponseEntity<AccountResponse> getAccountByAccountId(@PathVariable Long account_id) throws Exception {
        return new ResponseEntity<>(accountService.getAccountByAccountId(account_id), HttpStatus.OK);
    }
}
