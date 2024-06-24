package com.pismo.payment.transactions.controllers;

import com.pismo.payment.transactions.dtos.in.AccountDTO;
import com.pismo.payment.transactions.dtos.out.AccountResponse;
import com.pismo.payment.transactions.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Account Controller", tags = {"Accounts"}, description = "Account API's")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "")
    @ApiOperation(value = "Create a new account", notes = "This API creates one new account with the data requested.")
    public ResponseEntity<AccountResponse> createAccount(
            @ApiParam(value = "Dados da nova conta", required = true) @RequestBody AccountDTO accountDto) throws Exception {
        var account = accountService.createAccount(accountDto);
        return new ResponseEntity<>(AccountResponse.builder()
                .accountId(account.getAccountId())
                .documentNumber(account.getDocumentNumber())
                .build(),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/{account_id}")
    @ResponseBody
    @ApiOperation(value = "Get an account by an ID", notes = "This API returns the account details by the given ID.")
    public ResponseEntity<AccountResponse> getAccountByAccountId(
            @ApiParam(value = "ID da conta", required = true) @PathVariable Long account_id) throws Exception {
        var account = accountService.getAccountByAccountId(account_id);
        return new ResponseEntity<>(AccountResponse.builder()
                .accountId(account.getAccountId())
                .documentNumber(account.getDocumentNumber())
                .build(),
                HttpStatus.OK);
    }
}
