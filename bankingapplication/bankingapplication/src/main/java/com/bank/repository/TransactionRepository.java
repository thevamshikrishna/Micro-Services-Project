package com.bank.repository;

import com.bank.dto.TransactionView;
import com.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE t.account.accountId = :accountId")
    List<TransactionView> findTransactionsByAccountId(Long accountId);


}
