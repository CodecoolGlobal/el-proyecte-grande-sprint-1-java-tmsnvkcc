package com.codecool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "uuid")
  private UUID uuid;

  @Column(name = "registered_at")
  private Timestamp dateOfRegistration;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "email")
  private String email;

  @Column(name = "hashed_password")
  private String hashedPassword;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  @JsonManagedReference
  private Account account;

  @Column(name = "is_admin")
  private boolean isAdmin;

  public User() {}

  public User(String email, String hashedPassword, Account account, boolean isAdmin) {
    this.uuid = UUID.randomUUID();
    this.dateOfRegistration = new Timestamp(System.currentTimeMillis());
    this.userName = "CHANGE ME!";
    this.email = email;
    this.hashedPassword = hashedPassword;
    this.account = account;
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

  public Timestamp getDateOfRegistration() {
    return dateOfRegistration;
  }

  public void setDateOfRegistration(Timestamp dateOfRegistration) {
    this.dateOfRegistration = dateOfRegistration;
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

  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean admin) {
    isAdmin = admin;
  }

  @Override
  public String toString() {
    return String.format("[ENTITY]: User | [Id]: %s | [UUId]: %s | [DateOfRegistration]: %s | [UserName]: %s | [Email]: %s | [Account]: %s | [IsAdmin]: %s", id, uuid, dateOfRegistration, userName, email, account, isAdmin);
  }
}
