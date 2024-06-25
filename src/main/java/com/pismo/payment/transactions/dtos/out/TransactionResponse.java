package com.pismo.payment.transactions.dtos.out;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionResponse(long transaction_id, long account_id, long operation_type_id, BigDecimal amount, LocalDateTime event_date) {
}