package com.codecool.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid")
  private UUID uuid;

  @OneToOne(mappedBy = "accountId", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
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

  public Account() {}

  public Account(User user, String name, String description, String currency, double actualBalance, double savingsBalance) {
    this.user = user;
    this.name = name;
    this.description = description;
    this.currency = currency;
    this.actualBalance = actualBalance;
    this.savingsBalance = savingsBalance;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
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
}
