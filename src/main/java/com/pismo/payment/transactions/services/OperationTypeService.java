package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.operationType.OperationType;
import com.pismo.payment.transactions.exceptions.OperationTypeException;
import com.pismo.payment.transactions.repositories.OperationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationTypeService {

    private OperationTypeRepository operationTypeRepository;

    @Autowired
    public OperationTypeService(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    public OperationType getOperationTypeByOperationTypeId(Long operationTypeId) {
        return operationTypeRepository.findByOperationTypeId(operationTypeId).orElseThrow(() -> new OperationTypeException("OperationType invalid."));
    }
}

