package com.codecool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
  @JsonBackReference
  private User user;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "currency")
  private Currency currency;

  @Column(name = "actual_balance")
  private double actualBalance;

  @Column(name = "savings_balance")
  private double savingsBalance;

  @OneToMany(mappedBy = "account")
  @JsonIgnore
  private List<ExternalTransaction> externalTransactionList;

  @OneToMany(mappedBy = "account")
  @JsonIgnore
  private List<LocalTransaction> localTransactionList;

  public Account() {
    this.name = "CHANGE ME";
    this.description = "FILL ME IN";
    this.actualBalance = 0.0;
    this.savingsBalance = 0.0;
    this.externalTransactionList = new ArrayList<>();
    this.localTransactionList = new ArrayList<>();
  }

  @Override
  public String toString() {
    return String.format("[Id]: %s | [User]: %s | [Name]: %s | [Description]: %s | [Currency]: %s | [Actual Balance]: %s | [Savings Balance]: %s | [External Transactionlist]: %s | [Local Transactionlist]: %s", id, user, name, description, currency, actualBalance, savingsBalance, externalTransactionList, localTransactionList);
  }
}
