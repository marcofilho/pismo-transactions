package com.pismo.payment.transactions.repositories;

import com.pismo.payment.transactions.domain.operationType.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
    Optional<OperationType> findByOperationTypeId(Long operationTypeId);
}
