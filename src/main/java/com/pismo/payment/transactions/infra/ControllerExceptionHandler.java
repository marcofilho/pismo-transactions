package com.pismo.payment.transactions.infra;

import com.pismo.payment.transactions.dtos.out.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatAccountAlreadyExistsException(final DataIntegrityViolationException ex) {
        var exception = ExceptionDTO.builder().message("This document already has a created account.").statusCode("400").build();
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotFoundException(final EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGenericalException(final Exception ex) {
        var exception = ExceptionDTO.builder().message(ex.getMessage()).statusCode("500").build();
        return ResponseEntity.internalServerError().body(exception);
    }
}
