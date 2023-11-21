package com.codecool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid")
  private UUID uuid;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "email")
  private String email;

  @Column(name = "account_id")
  private int accountId;

  @Column(name = "is_admin")
  private boolean isAdmin;

  public User() {
  }

  public User(String userName, String email, int accountId, boolean isAdmin) {
    this.userName = userName;
    this.email = email;
    this.accountId = accountId;
    this.isAdmin = isAdmin;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean admin) {
    isAdmin = admin;
  }
}
