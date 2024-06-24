package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.domain.operationType.OperationType;
import com.pismo.payment.transactions.domain.transaction.Transaction;
import com.pismo.payment.transactions.dtos.in.TransactionDTO;
import com.pismo.payment.transactions.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Transaction createTransaction(TransactionDTO transactionDto) throws Exception {
        var account = accountService.getAccountByAccountId(transactionDto.account_id());
        var operationType = operationTypeService.getOperationTypeByOperationTypeId(transactionDto.operationType_id());
        var transaction = new Transaction();
        if (account != null && operationType != null) {
            transaction = Transaction.builder()
                    .account(Account.builder()
                            .accountId(transactionDto.account_id())
                            .build())
                    .operationsType(OperationType.builder()
                            .operationTypeId(transactionDto.operationType_id())
                            .build())
                    .amount(transactionDto.amount())
                    .build();
        }
        return transactionRepository.save(transaction);
    }
}
