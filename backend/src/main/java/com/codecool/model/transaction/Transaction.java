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


}
