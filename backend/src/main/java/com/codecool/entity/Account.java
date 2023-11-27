package com.codecool.entity;

import com.codecool.model.transaction.ExternalTransaction;
import com.codecool.model.transaction.LocalTransaction;
import com.codecool.model.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @OneToOne(mappedBy = "account", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
  @JsonBackReference
  private User user;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "currency")
  private String currency; // TODO should be updated to be an enum;

  @Column(name = "actual_balance")
  private double actualBalance;

  @Column(name = "savings_balance")
  private double savingsBalance;

  @Column(name = "ext_transactions")
  @OneToMany(mappedBy = "account")
  private List<ExternalTransaction> externalTransactionList;

  @Column(name = "loc_transactions")
  @OneToMany(mappedBy = "account")
  private List<LocalTransaction> localTransactionList;

  public Account() {
    this.name = "CHANGE ME";
    this.description = "FILL ME IN";
    this.currency = "HUF";
    this.actualBalance = 0.0;
    this.savingsBalance = 0.0;
    this.externalTransactionList = new ArrayList<>();
    this.localTransactionList = new ArrayList<>();
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public double getActualBalance() {
    return actualBalance;
  }

  public void setActualBalance(double actualBalance) {
    this.actualBalance = actualBalance;
  }

  public double getSavingsBalance() {
    return savingsBalance;
  }

  public void setSavingsBalance(double savingsBalance) {
    this.savingsBalance = savingsBalance;
  }

  public List<ExternalTransaction> getExternalTransactionList() {
    return externalTransactionList;
  }

  public void setExternalTransactionList(List<ExternalTransaction> externalTransactionList) {
    this.externalTransactionList = externalTransactionList;
  }

  public List<LocalTransaction> getLocalTransactionList() {
    return localTransactionList;
  }

  public void setLocalTransactionList(List<LocalTransaction> localTransactionList) {
    this.localTransactionList = localTransactionList;
  }
}
