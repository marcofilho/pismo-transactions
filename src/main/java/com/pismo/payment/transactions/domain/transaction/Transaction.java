package com.pismo.payment.transactions.domain.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.payment.transactions.domain.account.Account;
import com.pismo.payment.transactions.domain.operationType.OperationType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "transactionId")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "transaction_id")
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_type_id")
    @JsonIgnore
    private OperationType operationsType;

    @JsonProperty(value = "amount")
    @Column(name = "amount")
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_date", nullable = false, updatable = false)
    @CreatedDate
    @JsonProperty(value = "event_date")
    private LocalDateTime eventDate;
}
