package com.codecool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "description")
    protected String description;

    @Column(name = "date_of_transaction")
    @Temporal(TemporalType.DATE)
    protected LocalDate dateOfTransaction;

    @Column(name = "amount")
    protected double amount;

    @Column(name = "is_planned")
    protected boolean isPlanned;

    @Column(name = "is_recurring")
    protected boolean isRecurring;

    public Transaction( User user, String description, LocalDate dateOfTransaction, double amount, boolean isPlanned, boolean isRecurring) {
        this.user = user;
        this.description = description;
        this.dateOfTransaction = dateOfTransaction;
        this.amount = amount;
        this.isPlanned = isPlanned;
        this.isRecurring = isRecurring;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDate dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPlanned() {
        return isPlanned;
    }

    public void setPlanned(boolean planned) {
        isPlanned = planned;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }
}
