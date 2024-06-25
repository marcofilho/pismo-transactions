package com.pismo.payment.transactions.dtos.in;

import lombok.Builder;

@Builder
public record AccountRequest(Long accountId,
                             String documentNumber) {
}
