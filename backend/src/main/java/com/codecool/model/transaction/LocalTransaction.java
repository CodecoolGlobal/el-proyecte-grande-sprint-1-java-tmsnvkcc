package com.codecool.model.transaction;

import com.codecool.entity.Account;
import com.codecool.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "local_transactions")
public class LocalTransaction extends Transaction {

    @ManyToOne
    @JoinColumn(name = "account_id")
    protected Account account;

    public LocalTransaction(int id, User user, String description, LocalDate dateOfTransaction, double amount, boolean isPlanned, boolean isRecurring, Account account) {
        super(id, user, description, dateOfTransaction, amount, isPlanned, isRecurring);
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
