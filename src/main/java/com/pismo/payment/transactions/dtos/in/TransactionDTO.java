package com.pismo.payment.transactions.dtos.in;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount,
                             Long account_id,
                             Long operationType_id) {
}