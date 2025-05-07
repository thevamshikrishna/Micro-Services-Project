package com.bank.dto;

import com.bank.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.UUID;

//it is used to represent the view of a transaction
@JsonPropertyOrder({"transactionType", "transactionId", "account", "amount", "availableBalance", "timestamp"})

//
public interface TransactionView {
    UUID getTransactionId();

    TransactionType getTransactionType();

    LocalDateTime getTimestamp();

    double getAmount();

    double getAvailableBalance();

    String getUserName();

    AccountView getAccount();

    interface AccountView {
        Long getAccountId();

        String getAccountType();

    }
}

