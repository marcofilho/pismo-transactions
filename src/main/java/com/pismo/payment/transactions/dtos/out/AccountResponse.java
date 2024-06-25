package com.pismo.payment.transactions.dtos.out;

import lombok.Builder;

@Builder
public record AccountResponse(long account_id, String document_id) {
}
