package com.pismo.payment.transactions.domain.operationType;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity(name ="operations_types")
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
    private Long operationTypeId;

    @Column(name = "description")
    private String description;
}