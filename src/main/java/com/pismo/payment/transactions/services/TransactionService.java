package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.domain.operationType.OperationType;
import com.pismo.payment.transactions.domain.transaction.Transaction;
import com.pismo.payment.transactions.dtos.in.TransactionRequest;
import com.pismo.payment.transactions.dtos.out.TransactionResponse;
import com.pismo.payment.transactions.exceptions.AccountException;
import com.pismo.payment.transactions.exceptions.InvalidTransactionAmountException;
import com.pismo.payment.transactions.exceptions.OperationTypeException;
import com.pismo.payment.transactions.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private TransactionRepository transactionRepository;
    private AccountService accountService;
    private OperationTypeService operationTypeService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService, OperationTypeService operationTypeService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.operationTypeService = operationTypeService;
    }

    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        var account = accountService.getAccountByAccountId(transactionRequest.account_id());
        if (account == null)
            throw new AccountException("Account not found.");

        if (transactionRequest.operation_type_id() != 4) {
            if (transactionRequest.amount().compareTo(BigDecimal.ZERO) >= 0) {
                throw new InvalidTransactionAmountException("Operation not permitted.");
            }
        }

        if (transactionRequest.operation_type_id() == 4) {
            if (transactionRequest.amount().compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidTransactionAmountException("Operation not permitted.");
            }
        }

        var operationType = operationTypeService.getOperationTypeByOperationTypeId(transactionRequest.operation_type_id());
        if (operationType == null)
            throw new OperationTypeException("Operation type does not exist.");

        var transaction = Transaction.builder()
                .account(Account.builder()
                        .accountId(transactionRequest.account_id())
                        .build())
                .operationsType(OperationType.builder()
                        .operationTypeId(transactionRequest.operation_type_id())
                        .build())
                .amount(transactionRequest.amount())
                .eventDate(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .transaction_id(transaction.getTransactionId())
                .operation_type_id(transaction.getOperationsType().getOperationTypeId())
                .account_id(transaction.getAccount().getAccountId())
                .amount(transaction.getAmount())
                .event_date(transaction.getEventDate())
                .build();
    }
}
