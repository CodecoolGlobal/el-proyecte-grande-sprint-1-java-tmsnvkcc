package com.codecool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity(name = "local_transactions")
public class LocalTransaction extends Transaction {
    @ManyToOne
    @JoinColumn(name = "account_id")
    protected Account account;

    public LocalTransaction(User user, String description, LocalDate dateOfTransaction, double amount, boolean isPlanned, boolean isRecurring, Account account) {
        super(user, description, dateOfTransaction, amount, isPlanned, isRecurring);
        this.account = account;
    }

    public LocalTransaction() {
        super();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
