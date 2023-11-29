package com.codecool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity(name = "external_transactions")
public class ExternalTransaction extends Transaction {
    @ManyToOne
    @JoinColumn(name = "account_id")
    protected Account account;

    @OneToOne
    @JoinColumn(name = "category_id")
    protected TransactionCategory category;

    public ExternalTransaction(User user, String description, LocalDate dateOfTransaction, double amount, boolean isPlanned, boolean isRecurring, Account account) {
        super(user, description, dateOfTransaction, amount, isPlanned, isRecurring);
        this.account = account;
    }

    public ExternalTransaction() {}

    public String getCategoryName() {
        return "test";
    }

    @Override
    public String toString() {
        return "ExternalTransaction{" +
          "category=" + category +
          '}';
    }
}
