package com.pismo.payment.transactions.domain.operationType;

import jakarta.persistence.*;
import lombok.*;

@Entity(name ="operations_type")
@Table(name="operations_type")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "operationTypeId")
public class OperationType {

    @Id
    private Long operationTypeId;

    @Column(name = "description")
    private String description;
}