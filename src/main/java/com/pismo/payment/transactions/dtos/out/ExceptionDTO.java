package com.pismo.payment.transactions.dtos.out;

import lombok.Builder;

@Builder
public record ExceptionDTO(String message, String statusCode){
}
