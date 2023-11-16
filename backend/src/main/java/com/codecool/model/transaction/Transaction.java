package com.codecool.model.transaction;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Transaction {
    protected UUID uuid;
    protected int userId;
    protected int accountId;
    protected String description;
    protected LocalDate dateOfTransaction;
    protected int amount;
    protected boolean isPlanned;
    protected boolean isRecurring;

    public Transaction(UUID uuid, int userId, int accountId, String description, LocalDate dateOfTransaction, int amount, boolean isPlanned, boolean isRecurring) {
        this.uuid = uuid;
        this.userId = userId;
        this.accountId = accountId;
        this.description = description;
        this.dateOfTransaction = dateOfTransaction;
        this.amount = amount;
        this.isPlanned = isPlanned;
        this.isRecurring = isRecurring;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPlanned() {
        return isPlanned;
    }

    public boolean isRecurring() {
        return isRecurring;
    }
}
