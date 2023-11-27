package com.codecool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity(name = "external_transactions")
public class ExternalTransaction extends Transaction {
    @ManyToOne
    @JoinColumn(name = "account_id")
    protected Account account;

    @Column(name = "category_name")
    protected String categoryName;

    public ExternalTransaction(int id, User user, String description, LocalDate dateOfTransaction, double amount, boolean isPlanned, boolean isRecurring, Account account, String categoryName) {
        super(id, user, description, dateOfTransaction, amount, isPlanned, isRecurring);
        this.account = account;
        this.categoryName = categoryName;
    }

    public ExternalTransaction() {}

    public String getCategoryName() {
        return categoryName;
    }
}
