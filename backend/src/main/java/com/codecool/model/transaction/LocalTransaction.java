package com.codecool.model.transaction;

import java.time.LocalDate;
import java.util.UUID;

public class LocalTransaction extends Transaction {

    public LocalTransaction(UUID uuid, int userId, int accountId, String description, LocalDate dateOfTransaction, int amount, boolean isPlanned, boolean isRecurring) {
        super(uuid, userId, accountId, description, dateOfTransaction, amount, isPlanned, isRecurring);
    }

}
