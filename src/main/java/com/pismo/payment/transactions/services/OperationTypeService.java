package com.pismo.payment.transactions.services;

import com.pismo.payment.transactions.domain.operationType.OperationType;
import com.pismo.payment.transactions.repositories.OperationTypeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class OperationTypeService {
    private OperationTypeRepository operationTypeRepository;

    public OperationType getOperationTypeByOperationTypeId(Long operationTypeId) throws Exception {
        return operationTypeRepository.findByOperationTypeId(operationTypeId).orElseThrow(() -> new Exception("OperationType invalid."));
    }
}

