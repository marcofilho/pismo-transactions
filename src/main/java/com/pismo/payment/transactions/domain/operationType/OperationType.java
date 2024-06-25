package com.pismo.payment.transactions.domain.operationType;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="operations_types")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "operationTypeId")
public class OperationType {

    @Id
    @JsonProperty(value = "operation_type_id")
    @Column(name = "operation_type_id")
    private Long operationTypeId;

    @Column(name = "description", nullable = false)
    private String description;
}