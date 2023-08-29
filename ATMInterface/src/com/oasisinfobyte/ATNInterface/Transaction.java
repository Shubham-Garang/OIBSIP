package com.oasisinfobyte.ATNInterface;

import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType {
        WITHDRAWAL, DEPOSIT, TRANSFER
    }

    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
