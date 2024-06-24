package com.pismo.payment.transactions.controllers;

import com.pismo.payment.transactions.dtos.in.TransactionDTO;
import com.pismo.payment.transactions.dtos.out.TransactionResponse;
import com.pismo.payment.transactions.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
@Api(value = "Transaction Controller", tags = {"Transactions"}, description = "Transactions API's")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "")
    @ApiOperation(value = "Create a new transaction", notes = "This API creates a new transaction with the data requested.")
    public ResponseEntity<TransactionResponse> createTransaction(
            @ApiParam(value = "New transaction data payload", required = true) @RequestBody TransactionDTO transactionDTO) throws Exception {
        var transaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .operationTypeId(transaction.getOperationsType().getOperationTypeId())
                .accountId(transaction.getAccount().getAccountId())
                .amount(transaction.getAmount())
                .eventDate(transaction.getEventDate())
                .build(), HttpStatus.CREATED);
    }
}
