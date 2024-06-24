package com.pismo.payment.transactions.controllers;

import com.pismo.payment.transactions.domain.transaction.Transaction;
import com.pismo.payment.transactions.dtos.in.TransactionDTO;
import com.pismo.payment.transactions.dtos.out.TransactionResponse;
import com.pismo.payment.transactions.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
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
