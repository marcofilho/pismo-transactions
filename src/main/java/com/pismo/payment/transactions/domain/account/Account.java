package com.pismo.payment.transactions.domain.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Accounts")
@Table(name = "Accounts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "accountId")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "account_id")
    private Long accountId;

    @JsonProperty(value = "document_number")
    @Column(name = "documentNumber")
    private String documentNumber;

}
