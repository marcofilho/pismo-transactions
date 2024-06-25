package com.pismo.payment.transactions.dtos.in;

import java.math.BigDecimal;

public record TransactionRequest(BigDecimal amount,
                                 Long account_id,
                                 Long operation_type_id) {
}
