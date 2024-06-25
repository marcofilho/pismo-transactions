package com.pismo.payment.transactions.exceptions;

public class OperationTypeException extends RuntimeException{
    public OperationTypeException(String message) {
        super(message);
    }
}
