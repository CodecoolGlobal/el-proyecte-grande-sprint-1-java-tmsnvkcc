package com.codecool.model.transaction;

import java.time.LocalDate;
import java.util.UUID;

public class ExternalTransaction extends Transaction {
    protected String categoryName;

    public ExternalTransaction(UUID uuid, int userId, int accountId, String description, LocalDate dateOfTransaction, int amount, boolean isPlanned, boolean isRecurring, String categoryName) {
        super(uuid, userId, accountId, description, dateOfTransaction, amount, isPlanned, isRecurring);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
