package com.pismo.payment.transactions.repositories;


import com.pismo.payment.transactions.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByAccountId(Long accountId);

    Optional<Account> findByDocumentNumber(String documentNumber);
}
