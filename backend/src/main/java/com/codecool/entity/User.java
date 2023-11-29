package com.codecool.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "registered_at")
  private Timestamp dateOfRegistration;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "email")
  private String email;

  @Column(name = "hashed_password")
  private String hashedPassword;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  @JsonManagedReference
  private Account account;

  @Column(name = "is_admin")
  private boolean isAdmin;

  @ManyToMany
  @JoinTable(
    name = "categories_users_join",
    joinColumns = { @JoinColumn(name = "user_id") },
    inverseJoinColumns = { @JoinColumn(name = "category_id") }
  )
  @JsonManagedReference
  private List<TransactionCategory> categories;

  public User() {}

  public User(String email, String hashedPassword, Account account) {
    this.dateOfRegistration = new Timestamp(System.currentTimeMillis());
    this.userName = "CHANGE ME!";
    this.email = email;
    this.hashedPassword = hashedPassword;
    this.account = account;
    this.isAdmin = false;
    this.categories = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public List<TransactionCategory> getCategories() {
    return categories;
  }

  public void setCategories(List<TransactionCategory> categories) {
    this.categories = categories;
  }

  @Override
  public String toString() {
    return String.format("[ENTITY]: User | [Id]: %s | [DateOfRegistration]: %s | [UserName]: %s | [Email]: %s | [Account]: %s | [IsAdmin]: %s | [Categories]: %s", id, dateOfRegistration, userName, email, account, isAdmin, categories);
  }
}
