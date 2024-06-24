package com.pismo.payment.transactions.dtos.out;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionResponse(long transactionId, long accountId, long operationTypeId, BigDecimal amount, LocalDateTime eventDate) {
}