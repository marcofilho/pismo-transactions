package com.pismo.payment.transactions.dtos.out;

import lombok.Builder;

@Builder
public record AccountResponse(long accountId, String documentNumber) {
}
